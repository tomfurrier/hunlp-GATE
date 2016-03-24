package hu.nytud.gate.testing;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.log4j.Logger;

import gate.*;
import gate.creole.ANNIEConstants;
import gate.creole.ResourceInstantiationException;
import gate.util.GateException;

/**
 * Test Lang_Hungarian plugin with GATE Embedded (GATE API)
 * @author Márton Miháltz
 *
 */
public class PRTest {
	
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	public void init() throws GateException {
		// Initialize GATE Embedded
		Gate.init();		
	}
	
	public static Document createDoc() throws ResourceInstantiationException {
		// Create a new GATE Document LR with the following text:
		String sentstr = "Alma";
		//String sentstr = "A nagy kutya gyorsan fut. Én küldtem árvíztűrő nyélen.";
		//String sentstr = "The President is in Washington, DC. He is safe.";
		return Factory.newDocument(sentstr);		
	}
	
	public void loadLangHungarian(boolean useUserPluginDir) throws MalformedURLException, GateException, URISyntaxException {
		// Load the Lang_Hungarian plugin
		// useUserPluginDir: if true, load it from user plugin dir/Lang_Hungarian, otherwise use dir of current jar file 
		URL pluginDir;
		if (useUserPluginDir) {
			pluginDir = new File(Gate.getUserConfig().getString("gate.user.plugins"), "Lang_Hungarian").toURI().toURL();
		}
		else {
			File jarFile = new File(PRTest.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			pluginDir = jarFile.getParentFile().toURI().toURL();
		}
		System.out.format("plugindir=%s\n", pluginDir.toString());
		//pluginDir = new File("/home/mm/Infra2/hunlp-GATE/Lang_Hungarian").toURI().toURL();		
		Gate.getCreoleRegister().registerDirectories(pluginDir);
	}
	
	/**
	 * Create a Document, apply tokenizer and POS-tagger/lemmatizer, dump annotations to stdout
	 */
	public void testMLTokPOS() {
		
		try {
			
			this.init();
			
			Document doc = PRTest.createDoc();
									
			loadLangHungarian(true);
		
			// Create a new MagyarlancSentenceSplitterTokenizer PR, apply it on the document
			ProcessingResource tok = (ProcessingResource)Factory.createResource(
					"hu.nytud.gate.tokenizers.MagyarlancSentenceSplitterTokenizer");
			tok.setParameterValue("document", doc);
			tok.execute();
			
			// Create a new MagyarlancPOSTaggerLemmatizer PR, apply it on the document
			ProcessingResource tagger = (ProcessingResource)Factory.createResource(
					"hu.nytud.gate.postaggers.MagyarlancPOSTaggerLemmatizer");
			tagger.setParameterValue("document", doc);
			tagger.execute();			
			
			// Dump document's annotations to stdout
			System.out.println(doc.toString());		
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create a document, add an annotation with a String[]-valued feature, dump XML
	 */
	public void testFeats() {

		try {

			init();

			Document doc = PRTest.createDoc();
			
			FeatureMap features = Factory.newFeatureMap();
		    features.put("anas", new String[] {"alma", "alom"});
		    doc.getAnnotations().add(0L, 1L, "Token", features);
		    
			System.out.println(doc.toXml());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	/**
	 * Create a Document, apply tokenizer, morphanalizer, dump annotations to stdout
	 */
	public void testMLMoraAna() {
		
		try {
			
			this.init();
			
			Document doc = PRTest.createDoc();
									
			loadLangHungarian(true);
		
			// Create a new MagyarlancSentenceSplitterTokenizer PR, apply it on the document
			ProcessingResource tok = (ProcessingResource)Factory.createResource(
					"hu.nytud.gate.tokenizers.MagyarlancSentenceSplitterTokenizer");
			tok.setParameterValue("document", doc);
			tok.execute();
			
			// Create a new MagyarlancMSDMorphAnalyzer PR, apply it on the document
			ProcessingResource analyzer = (ProcessingResource)Factory.createResource(
					"hu.nytud.gate.morph.MagyarlancMSDMorphAnalyzer");
			analyzer.setParameterValue("document", doc);
			analyzer.execute();			
			
			// Dump document's annotations to stdout
			System.out.println(doc.toString());
			//System.out.println(doc.toXml());
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		
	/**
	 * Create a Document, apply tokenizer, KR morphanalizer, dump annotations to stdout
	 */
	public void testMLKRMoraAna() {
		
		try {
			
			this.init();
			
			Document doc = PRTest.createDoc();
									
			loadLangHungarian(true);
		
			// Create a new MagyarlancSentenceSplitterTokenizer PR, apply it on the document
			ProcessingResource tok = (ProcessingResource)Factory.createResource(
					"hu.nytud.gate.tokenizers.MagyarlancSentenceSplitterTokenizer");
			tok.setParameterValue("document", doc);
			tok.execute();
			
			// Create a new MagyarlancKMorphAnalyzer PR, apply it on the document
			ProcessingResource analyzer = (ProcessingResource)Factory.createResource(
					"hu.nytud.gate.morph.MagyarlancKRMorphAnalyzer");
			analyzer.setParameterValue("document", doc);
			analyzer.execute();			
			
			// Dump document's annotations to stdout
			System.out.println(doc.toString());
			//System.out.println(doc.toXml());
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Create a Document, apply tokenizer + POS-tagger/lemmatizer + dep. parser, dump annotations to stdout
	 */
	public void testMLTokPOSParse() {
		
		try {
			
			this.init();
			
			Document doc = PRTest.createDoc();
									
			loadLangHungarian(true);
		
			// Create a new MagyarlancSentenceSplitterTokenizer PR, apply it on the document
			ProcessingResource tok = (ProcessingResource)Factory.createResource(
					"hu.nytud.gate.tokenizers.MagyarlancSentenceSplitterTokenizer");
			tok.setParameterValue("document", doc);
			tok.execute();
			
			// Create a new MagyarlancPOSTaggerLemmatizer PR, apply it on the document
			ProcessingResource tagger = (ProcessingResource)Factory.createResource(
					"hu.nytud.gate.postaggers.MagyarlancPOSTaggerLemmatizer");
			tagger.setParameterValue("document", doc);
			tagger.execute();			

			// Create a new MagyarlancDependencyParser PR, apply it on the document
			ProcessingResource parser = (ProcessingResource)Factory.createResource(
					"hu.nytud.gate.parsers.MagyarlancDependencyParser");
			parser.setParameterValue("document", doc);
			parser.setParameterValue("addPosTags", true);
			parser.setParameterValue("addMorphFeatures", true);
			parser.execute();						
			
			// Dump document's annotations to stdout
			System.out.println(doc.toString());		
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Load an ANNIE PR and run it
	 */
	public void testANNIE() {

		try {
		
			this.init();
			
			Gate.getCreoleRegister().registerDirectories(new File( 
					Gate.getPluginsHome(), ANNIEConstants.PLUGIN_DIR).toURI().toURL());			
			
			Document doc = PRTest.createDoc();
			
			FeatureMap params = Factory.newFeatureMap(); //empty map:default params 
			ProcessingResource tagger = (ProcessingResource)Factory.createResource("gate.creole.tokeniser.DefaultTokeniser", params);			
			tagger.setParameterValue("document", doc);
			tagger.execute();
			
			// Dump document's annotations to stdout
			System.out.println(doc.toString());	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Various tests with quntoken
	 */
	public void testQuntoken() {
		
		try {
			
			this.init();
			
			Document doc = PRTest.createDoc();
									
			loadLangHungarian(true);
		
			// Create a new QunTokenCommandLine PR, apply it on the document
			ProcessingResource qtok = (ProcessingResource)Factory.createResource(
					"hu.nytud.gate.tokenizers.QunTokenCommandLine");
			qtok.setParameterValue("document", doc);
			qtok.execute();
			
			// Dump document's annotations to stdout
			System.out.println("Quntoken tagged contents:");
			System.out.println(doc.toString());
				
			// Create a new HunPosCommandLine PR, apply it on the document
			ProcessingResource tagger = (ProcessingResource)Factory.createResource(
					"hu.nytud.gate.postaggers.HunPosCommandLine");
			tagger.setParameterValue("document", doc);
			tagger.execute();
								
			// Dump document's annotations to stdout
			System.out.println(doc.toString());
						 
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}		
	
	public static void main(String[] args) {
		System.out.println("Hello, test");
		PRTest t = new PRTest();
		//t.testANNIE();
		//t.testMLTokPOS();
		//t.testFeats();
		//t.testMLMoraAna();
		//t.testMLKRMoraAna();
		//t.testMLTokPOSParse();
		t.testQuntoken();
	}
	
}
