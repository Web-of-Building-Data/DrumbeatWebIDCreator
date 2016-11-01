package fi.aalto.cs.drumbeat.vo;

/**
 * @author joraskur
 *
 */
public class Data {
	private String CERT_AUTH_ALIAS = "DRUMBEAT";
	private String CERT_AUTH_KEYSTORE_FILE = "CertificateAuthorityKeyStore.jks";
	private String CERT_AUTH_KEYSTORE_PASSWORD = "password_jajakjakjwkjskjw3i9390i3";
	
	private String CERT_AUTH_PRIVATE_KEY_FILE = "CertificateAuthorityPrivateKey.pem";
	private String CERT_AUTH_PUBLIC_KEY_FILE = "CertificateAuthorityPublicKey.pem";
	private String CERT_AUTH_CERTIFICATE_FILE = "CertificateAuthorityCertificate.pem";

	
	private String CLIENT_ALIAS = "DRUMBEAT";
	private String CLIENT_KEYSTORE_FILE = "ClientKeyStore.p12";
	private String CLIENT_KEYSTORE_PASSWORD = "password_jajakjakjwkjskjw3i9390i3";
	
	private final ClientCertificate client_certificate=new ClientCertificate();
	private final CACertificate ca_certificate=new CACertificate();
	
	// WebID Profile URL
	private String CLIENT_SUBJECT_ALT_NAME_URI ="https://www.cs.hut.fi/~joraskar/webid/jyrkio#me";

	public static enum Singleton {
		INSTANCE;

		private static final Data singleton = new Data();

		public Data getSingleton() {
			return singleton;
		}
	}

	public String getCERT_AUTH_ALIAS() {
		return CERT_AUTH_ALIAS;
	}

	public void setCERT_AUTH_ALIAS(String cERT_AUTH_ALIAS) {
		CERT_AUTH_ALIAS = cERT_AUTH_ALIAS;
	}

	public String getCERT_AUTH_KEYSTORE_FILE() {
		return CERT_AUTH_KEYSTORE_FILE;
	}

	public void setCERT_AUTH_KEYSTORE_FILE(String cERT_AUTH_KEYSTORE_FILE) {
		CERT_AUTH_KEYSTORE_FILE = cERT_AUTH_KEYSTORE_FILE;
	}

	public String getCERT_AUTH_KEYSTORE_PASSWORD() {
		return CERT_AUTH_KEYSTORE_PASSWORD;
	}

	public void setCERT_AUTH_KEYSTORE_PASSWORD(String cERT_AUTH_KEYSTORE_PASSWORD) {
		CERT_AUTH_KEYSTORE_PASSWORD = cERT_AUTH_KEYSTORE_PASSWORD;
	}

	public String getCERT_AUTH_PRIVATE_KEY_FILE() {
		return CERT_AUTH_PRIVATE_KEY_FILE;
	}

	public void setCERT_AUTH_PRIVATE_KEY_FILE(String cERT_AUTH_PRIVATE_KEY_FILE) {
		CERT_AUTH_PRIVATE_KEY_FILE = cERT_AUTH_PRIVATE_KEY_FILE;
	}

	public String getCERT_AUTH_PUBLIC_KEY_FILE() {
		return CERT_AUTH_PUBLIC_KEY_FILE;
	}

	public void setCERT_AUTH_PUBLIC_KEY_FILE(String cERT_AUTH_PUBLIC_KEY_FILE) {
		CERT_AUTH_PUBLIC_KEY_FILE = cERT_AUTH_PUBLIC_KEY_FILE;
	}

	public String getCERT_AUTH_CERTIFICATE_FILE() {
		return CERT_AUTH_CERTIFICATE_FILE;
	}

	public void setCERT_AUTH_CERTIFICATE_FILE(String cERT_AUTH_CERTIFICATE_FILE) {
		CERT_AUTH_CERTIFICATE_FILE = cERT_AUTH_CERTIFICATE_FILE;
	}

	public String getCLIENT_ALIAS() {
		return CLIENT_ALIAS;
	}

	public void setCLIENT_ALIAS(String cLIENT_ALIAS) {
		CLIENT_ALIAS = cLIENT_ALIAS;
	}

	public String getCLIENT_KEYSTORE_FILE() {
		return CLIENT_KEYSTORE_FILE;
	}

	public void setCLIENT_KEYSTORE_FILE(String cLIENT_KEYSTORE_FILE) {
		CLIENT_KEYSTORE_FILE = cLIENT_KEYSTORE_FILE;
	}

	public String getCLIENT_KEYSTORE_PASSWORD() {
		return CLIENT_KEYSTORE_PASSWORD;
	}

	public void setCLIENT_KEYSTORE_PASSWORD(String cLIENT_KEYSTORE_PASSWORD) {
		CLIENT_KEYSTORE_PASSWORD = cLIENT_KEYSTORE_PASSWORD;
	}

	public String getCLIENT_SUBJECT_ALT_NAME_URI() {
		return CLIENT_SUBJECT_ALT_NAME_URI;
	}

	public void setCLIENT_SUBJECT_ALT_NAME_URI(String cLIENT_SUBJECT_ALT_NAME_URI) {
		CLIENT_SUBJECT_ALT_NAME_URI = cLIENT_SUBJECT_ALT_NAME_URI;
	}

	public ClientCertificate getClient_certificate() {
		return client_certificate;
	}

	public CACertificate getCa_certificate() {
		return ca_certificate;
	}


	
	
}
