import com.esliceu.service.RoverService;
import com.eslicue.model.CameraAbbr;
import com.eslicue.model.RoverName;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        RoverService roverService = new RoverService();
        roverService.getByMartianSol(100, CameraAbbr.FHAZ,1, RoverName.curiosity);
    }
}
