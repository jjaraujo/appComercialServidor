package br.appcomercial.servidor.controller.firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Message.Builder;
import com.google.firebase.messaging.Notification;

import br.appcomercial.servidor.controller.VariaveisControle;

public class Firebase {

	String token = 				 "dSVkxcl_b54:APA91bE1ziWhlZ6I6cIwduEe-YHaDnC1e5ob8yJ1Q_y1866bidd5b2T2wCiyktfWNCtlLDJshmEMcrVFa-hmgzecg1Js_Qv2Bbs30A5A3pRx0ijesHYevGdx7f-mgmXqdXMLL4pyliFHAIzb-8HXwjTmLSKeDimajw";
	public void create() {
		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream(
					"D:\\eclipse-workspace\\appcomercial\\src\\main\\webapp\\WEB-INF\\appcomercialkey.json");

			// token.
			// cr.of;
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://appcomercial-dd7e5.firebaseio.com").build();
			 VariaveisControle.app = VariaveisControle.app == null ? FirebaseApp.initializeApp(options) : VariaveisControle.app;
			FirebaseApp app = VariaveisControle.app;
			FirebaseMessaging fm = FirebaseMessaging.getInstance(app);
			Builder builder = Message.builder();
			Notification notification = new Notification("Teste Titulo", "Teste Corpo");
			builder.putData("TESTE", "TESTE");
			builder.setToken(token);
			Message msg = builder.build();
			fm.send(msg);
			System.out.println("Msg enviada: " + msg.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FirebaseMessagingException e) {
			// TODO Auto-generated catch block
			Throwable o = e.getCause();
			String s = e.getErrorCode();
			// registration-token-not-registered
			e.printStackTrace();
		}
	}

	private FirebaseToken validaToken(FirebaseApp app) {
		try {
			return FirebaseAuth.getInstance(app).verifyIdToken(new FirebaseConfig().getApikey());
		} catch (FirebaseAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
