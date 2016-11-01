package fi.aalto.cs.drumbeat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.bc.BcX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;


public class CertificateCommons {
	private static final String PROVIDER_NAME = BouncyCastleProvider.PROVIDER_NAME;
	private static final String KEY_GENERATION_ALGORITHM = "RSA";
	private static final String SIGNATURE_ALGORITHM = "SHA256WithRSAEncryption";
	public static final Date NOT_BEFORE = new Date(System.currentTimeMillis() - 86400000L * 365);
	public static final Date NOT_AFTER = new Date(System.currentTimeMillis() + 86400000L * 365 * 100);
	

	public static X509Certificate signCertificate(X509v3CertificateBuilder certificateBuilder,
			PrivateKey signedWithPrivateKey) throws OperatorCreationException, CertificateException {
		ContentSigner signer = new JcaContentSignerBuilder(SIGNATURE_ALGORITHM).setProvider(PROVIDER_NAME)
				.build(signedWithPrivateKey);
		return new JcaX509CertificateConverter().setProvider(PROVIDER_NAME)
				.getCertificate(certificateBuilder.build(signer));
	}


	public static KeyPair generateKeyPair(int keySize) {
		KeyPairGenerator generator=null;
		try {
			generator = KeyPairGenerator.getInstance(KEY_GENERATION_ALGORITHM, PROVIDER_NAME);
			generator.initialize(keySize, new SecureRandom());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generator.generateKeyPair();
	}

	public static SubjectKeyIdentifier createSubjectKeyIdentifier(Key key) throws IOException {
		ASN1InputStream is = null;
		try {
			is = new ASN1InputStream(new ByteArrayInputStream(key.getEncoded()));
			ASN1Sequence seq = (ASN1Sequence) is.readObject();
			@SuppressWarnings("deprecation")
			SubjectPublicKeyInfo info = new SubjectPublicKeyInfo(seq);
			return new BcX509ExtensionUtils().createSubjectKeyIdentifier(info);			
		} finally {
			is.close();
		}
	}
}
