package com.devoteam.VehicleApplication;

import com.devoteam.VehicleApplication.service.ApplicationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@Log4j2
@SpringBootApplication
public class VehicleApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleApplication.class, args);

        Scanner inputForSwitchMenu = new Scanner(System.in);
        boolean looping = true;
        ApplicationService applicationService = new ApplicationService();

        while (looping) {

            applicationService.menuLogic();
            int automakerMenuChoice = inputForSwitchMenu.nextInt();

//            switch (automakerMenuChoice) {
//                case 1 -> applicationService.menuOption1FindByAutomaker();
//                case 2 -> applicationService.menuOption2FindByModel();
//                case 3 -> applicationService.menuOption3SaveByModel();
//                case 4 -> applicationService.menuOption4UpdateByModel();
//                case 5 -> applicationService.menuOption5DeleteByModel();
//                case 6 -> applicationService.menuOption6ShowAllVehicles();
//                case 0 -> {
//                    return;
//                }
//                default -> log.info("Please, select on of the above options");
//            }

            boolean gettingAnswerFromUser = true;
            while (gettingAnswerFromUser) {
                Scanner inputForChooseToContinueMenu = new Scanner(System.in);

                log.info("\nDo you wish to continue? \n");
                log.info("1. Yes");
                log.info("2. No");

                int chooseToContinue = inputForChooseToContinueMenu.nextInt();
                boolean continuingApplication = chooseToContinue == 1;
                boolean quittingApplication = chooseToContinue == 2;

                if (continuingApplication) {
                    gettingAnswerFromUser = false;
                } else if (quittingApplication) {
                    gettingAnswerFromUser = false;
                    looping = false;
                } else {
                    log.error("Please pick a valid option");
                }
            }
        }
    }
}