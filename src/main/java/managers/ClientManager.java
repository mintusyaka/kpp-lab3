package managers;

import models.Client;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ClientManager {
    public static void writeClientsToFile(List<Client> clients, String filename) {
        File file = new File(filename);
        if(file.exists()) {
            file.delete();
        }

        try(BufferedWriter clientsWriter = new BufferedWriter(new FileWriter(filename, false)))
        {
            for(Client client : clients)
            {
                clientsWriter.write(client.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Client> readClientsFromFile(String fileName, List<Client> clients) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            while (true) {
                String line = null;
                line = reader.readLine();
                if(line == null || line.length() <= 1)
                    break;
                String[] lines = line.split("\\|");

                Client client = new Client(Integer.parseInt(lines[0].split(":")[1]), lines[1].split(":")[1], Integer.parseInt(lines[2].split(":")[1]));

                clients.add(client);
            }

        } catch (IOException e) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Error reading clients. Do you want to generate new list?");
            String command = scanner.nextLine();
            if(command.equals("yes"))
            {
                ClientsGenerator.generate(clients);
            }
            else {
                System.out.println("Bye-bye!");
                clients.clear();
                return null;
            }
        }
        return clients;
    }

    public static class ClientsGenerator {
        private static void fillClients(List<Client> clients)
        {
            Random rand = new Random();

            if(clients.isEmpty())
            {
                clients.add(
                        new Client(
                                clients.size(),
                                "George",
                                rand.nextInt(9) + 1
                        )
                );
                clients.add(
                        new Client(
                                clients.size(),
                                "Steven",
                                rand.nextInt(9) + 1
                        )
                );
                clients.add(
                        new Client(
                                clients.size(),
                                "Lucky",
                                rand.nextInt(9) + 1
                        )
                );
                clients.add(
                        new Client(
                                clients.size(),
                                "Felix",
                                rand.nextInt(9) + 1
                        )
                );
                clients.add(
                        new Client(
                                clients.size(),
                                "Anna",
                                rand.nextInt(9) + 1
                        )
                );
            }
        }

        public static void generate(List<Client> clients) {
            fillClients(clients);
        }
    }

}

