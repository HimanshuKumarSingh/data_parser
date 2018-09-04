package com.marlabs.validate;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import com.marlabs.log.LogTrace;

public class XMLValidator {

	public static void validateXmlAgainstXsd(String xmlPath, String[] xsdPath) {
		if (xmlPath == null || xmlPath.isEmpty()) {
			LogTrace.log.error("ERROR: path/name of the xml cannot be null.");
			return;
		}
		if (xsdPath == null || xsdPath.length < 1) {
			LogTrace.log.error("ERROR: please provide atleast one xsd file to validate the provided xml.");
			return;
		}
		final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		final StreamSource[] xsdSource = generateStreamSourcesFromXsdPathsJdk8(xsdPath);
		try {
			final Schema schema = schemaFactory.newSchema(xsdSource);
			final Validator validator = schema.newValidator();
			LogTrace.log.info("Validating " + xmlPath + " againt " + Arrays.toString(xsdPath) + ".....");
			validator.validate(new StreamSource(new File("xmlPath")));
		} catch (IOException | SAXException e) {
			LogTrace.log.error(
					"ERROR:unable to validate " + xmlPath + " againest xsd " + Arrays.toString(xsdPath) + "--" + e);

		}
		LogTrace.log.info("validation completed");

	}

	private static StreamSource[] generateStreamSourcesFromXsdPathsJdk8(String[] xsdPath) {

		return Arrays.stream(xsdPath).map(StreamSource::new).collect(Collectors.toList())
				.toArray(new StreamSource[xsdPath.length]);
	}
}
