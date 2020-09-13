package info.kring.keycloak;


import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.ClientRepresentation;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8280/auth")
                .realm("alfresco")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("admin-cli")
                .clientSecret("48cfa07e-adff-4a63-a17c-493f84546f2b")
                .build();


//        Keycloak keycloak = Keycloak.getInstance("http://localhost:8280/auth","alfresco", null, null, "admin-cli", "48cfa07e-adff-4a63-a17c-493f84546f2b");
//        TokenManager tokenManager = keycloak.tokenManager();
//        AccessTokenResponse tokenResponse = tokenManager.getAccessToken();


//        AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
//        String token = tokenResponse.getToken();
//
//        System.out.println(token);

        RealmResource realmResource = keycloak.realm("alfresco");
        ClientsResource clientsResource = realmResource.clients();

//        ClientResource alfrescoClientResource = clientsResource.get("6a3422ce-92e7-4d71-a705-116dc3e85bf3");
//        ClientRepresentation alfrescoClientRepresentation = alfrescoClientResource.toRepresentation();

        ClientRepresentation alfrescoClientRepresentation = clientsResource.findByClientId("alfresco").get(0);
        ClientResource alfrescoClientResource = clientsResource.get(alfrescoClientRepresentation.getId());

        boolean enabled = alfrescoClientRepresentation.isEnabled();
        System.out.println(enabled);
        alfrescoClientRepresentation.setEnabled(!enabled);

        alfrescoClientResource.update(alfrescoClientRepresentation);

//        for (int i = 0; i < 200; i++) {
//            enabled = alfrescoClientRepresentation.isEnabled();
//            System.out.println(i);
//            System.out.println(enabled);
//            alfrescoClientRepresentation.setEnabled(!enabled);
//            alfrescoClientResource.update(alfrescoClientRepresentation);
//
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
