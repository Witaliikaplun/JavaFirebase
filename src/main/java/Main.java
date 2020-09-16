import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sun.security.mscapi.CPublicKey;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    private static final String UID = "public_resource";
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        final FirebaseDatabase myDB;
        DatabaseReference myDBRef;
        FileInputStream serviceAccount;
        final String MY_DATABASE = "mydatabase";
        FirebaseOptions options = null;
        Scanner scanner = new Scanner(System.in);


            serviceAccount = new FileInputStream("./ServiceAccountKey.json");
            options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://testfirebase3-24075.firebaseio.com")
                    .build();
        FirebaseApp.initializeApp(options);

        //код отработал!!! удалось записать в CloudFirestory-----------------------------------------------------
        /*
        Firestore db = FirestoreClient.getFirestore();
        Main getQuote = new Main();
        HashMap<String, String> quote = getQuote.getQuoteFromHTTP();
        ApiFuture<WriteResult> future = db.collection(MY_DATABASE).document("inspiration").set(quote);
        System.out.println("susesful:" + future.get().getUpdateTime());
        */
        //код отработал!!! удалось записать в CloudFirestory-----------------------------------------------------


        //код рабочий отправляет на сервер в базу данных :)))
        /*final HashMap<String, Model> myHashMap = new HashMap<>();
        myHashMap.put("first2", new Model("Петр", "Дроздов", "Машинист Intellij IDEA8"));
        myHashMap.put("second2", new Model("Василий", "Динозавров", "Таксист Intellij IDEA8"));
        myHashMap.put("third2", new Model("ddd", "Попов", "Тракторист Intellij IDEA8"));

        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users1").document("alovelace");
        ApiFuture<WriteResult> result = docRef.set(myHashMap);
        System.out.println("susesfull: " + result.get().getUpdateTime());*/
        //-------------------------------------------------------

        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users1").document("alovelace");
        String in = "";
        String key = "key";
        int count = 0;
        HashMap<String, String> message = new HashMap<>();
        while (!in.equals("end")){
            in = scanner.nextLine();
            message.put(key + count, in);
            count++;
            ApiFuture<WriteResult> result = docRef.set(message);
            System.out.println("susesfull: " + result.get().getUpdateTime());
        }






//        Map<String, Object> additionalClaims = new HashMap<>();
//        additionalClaims.put("premiumAccount", true);
//        String customToken = FirebaseAuth.getInstance().createCustomTokenAsync(UID, additionalClaims).get();
//        System.out.println(customToken);











        System.out.println("FIN");
    }
    //код отработал!!! удалось записать в CloudFirestory-----------------------------------------------------
    /*public HashMap<String, String> getQuoteFromHTTP() throws IOException{
        return new HashMap<>();
    }*/
    //код отработал!!! удалось записать в CloudFirestory-----------------------------------------------------

}
