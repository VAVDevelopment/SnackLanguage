package snackLanguage.rest.Resources;

import org.codehaus.jackson.map.ObjectMapper;
import snackLanguage.Service.Impl.UsersServiceImpl;
import snackLanguage.dao.Entities.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Path(value = "/authorization")
public class RegistrationResources {

    @POST
    @Path(value = "/check")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkAuthorization(InputStream is){

        UserEntity user = readUser(is);

        if (user != null){
            String checkAuthorization = checkUser(user.getUser_email());
            return checkAuthorization;
        }
        return "invalid";
    }

    private UserEntity readUser(InputStream is){
        UserEntity user = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            user = objectMapper.readValue(is, UserEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    private String checkUser(String email){

        UsersServiceImpl usersService = new UsersServiceImpl();
        List<UserEntity> usersList = usersService.getAllUsers();

        for (int i = 0; i < usersList.size(); i++){
            if (usersList.get(i).getUser_email().equals(email)){
                return "valid";
            }
        }
        return "invalid";
    }
}
