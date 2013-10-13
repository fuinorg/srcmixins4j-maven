package org.fuin.srcmixins4j.maven;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.junit.Before;
import org.junit.Test;

public class SrcMixins4JMojoTest {

	private static final File TEST_DIR = new File(
			"target/test-classes/test-project");

	private Verifier verifier;

	@Before
	public void setup() throws Exception {
		verifier = new Verifier(TEST_DIR.getAbsolutePath());
		verifier.deleteArtifacts("org.fuin.srcmixins4j",
				"srcmixins4j-test-project", "0.0.1");
	}

	@Test
	public void testMojo() throws VerificationException {

		// PREPARE
		final File actualFile = new File(TEST_DIR, "src/main/java/org/fuin/srcmixins4j/test/TestClass.java");
		final File expectedFile = new File("src/test/resources/ExpectedTestClass.java");
		
		// TEST
		verifier.executeGoal("org.fuin.srcmixins4j:srcmixins4j-maven-plugin:process-mixins");
		
		// VERIFY
		verifier.verifyErrorFreeLog();
		assertThat(actualFile).hasSameContentAs(expectedFile);

	}

}
