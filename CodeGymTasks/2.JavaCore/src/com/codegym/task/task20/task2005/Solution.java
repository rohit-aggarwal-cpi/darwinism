package com.codegym.task.task20.task2005;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* 
Stranger things

*/

public class Solution {
    public static void main(String[] args) {
        // Update the string passed to the createTempFile method based on the path to a file on your hard drive
        try {
            File yourFile = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            Human smith = new Human ("Smith", new Asset ("home"), new Asset ("car"));
            smith.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            // Check that smith is equal to somePerson
            System.out.println(smith.equals(somePerson));
            inputStream.close();

        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (!name.equals(human.name)) {
                return false;
            }
            return Objects.equals(assets, human.assets);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            // Implement this method
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println(this.name);
            if (this.assets != null && this.assets.size() > 0) {
                for (Asset current : this.assets)
                    printWriter.println(current.getName());
            }
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            // Implement this method
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            this.name = reader.readLine();
            String assetName;
            if (this.assets == null) {
                this.assets = new ArrayList<>();
            }
            while ((assetName = reader.readLine()) != null)
                this.assets.add(new Asset(assetName));
            reader.close();
        }
    }
}