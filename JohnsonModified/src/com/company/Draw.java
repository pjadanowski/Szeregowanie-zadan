package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Draw {

    public static void draw(Task[][] schedule) throws IOException {

        Files.deleteIfExists(Paths.get("rysunek.html"));

        File file = new File("rysunek.html");
        FileWriter fout = new FileWriter(file, true);

        fout.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n");


        String first = "\n" +
                "<svg width=\"1200\" height=\"70\">" +
                "\n" +
                "  <text x=\"0\" y=\"50\" fill=\"#227c2a\">M1</text>";
        for (int i = 0; i < schedule[0].length; i++) {
            if (schedule[0][i] != null) {
            first += "<rect x=\""+ (30 + i*50) +"\" y=\"20\" width=\"50\" height=\"50\"\n" +
                    "  style=\"fill:#c6ffcb;stroke:#77ea81;stroke-width:2;fill-opacity:0.9;stroke-opacity:0.99\" />\n" +
                    "  <text x=\""+( 50+ 50*i)+"\" y=\"50\" fill=\"#227c2a\">"+schedule[0][i].name+"</text>";
            }
        }
        first += "  </svg>\n" +
                "\n" +
                "<br>";

//        second machine
        String second = "  <!-- druga maszyna -->\n" +
                "<svg width=\"1200\" height=\"50\">\n" +
                "\n" +
                "  <text x=\"0\" y=\"30\" fill=\"#227c2a\">M2</text>";

        for (int i = 0; i < schedule[1].length; i++) {
            if (schedule[1][i] != null) {
                if (schedule[1][i].name != "  ") {

                second += "<rect x=\""+ (30 + i*50) +" \" y=\"0\" width=\"50\" height=\"50\"\n" +
                        "  style=\"fill:#c6ffcb;stroke:#77ea81;stroke-width:2;fill-opacity:0.9;stroke-opacity:0.99\" />\n" +
                        "  <text x=\""+( 50+ 50*i)+"\" y=\"30\" fill=\"#227c2a\">" + schedule[1][i].name + "</text>";
                }
                else
                {
                    second += "<rect x=\""+ (30 + i*50) +" \" y=\"0\" width=\"50\" height=\"50\"\n" +
                            "  style=\"fill:#CFCFCF;stroke:#4d4d4d;stroke-width:2;fill-opacity:0.2;stroke-opacity:0.15\" />\n" +
                            "  <text x=\""+( 50+ 50*i)+"\" y=\"30\" fill=\"#227c2a\">" + schedule[1][i].name + "</text>";
                }
            }
        }
        second += "  </svg>\n" +
                "\n" +
                "\n";




        //        third machine
        String third = "  <!-- trzecia maszyna -->\n" +
                "<svg width=\"1400\" height=\"80\">\n" +
                "\n" +
                "  <text x=\"0\" y=\"30\" fill=\"#227c2a\">M3</text>";

        for (int i = 0; i < schedule[2].length; i++) {
            if (schedule[2][i] != null) {
                if (schedule[2][i].name != "  ") {

                    third += "<rect x=\""+ (30 + i*50) +" \" y=\"0\" width=\"50\" height=\"50\"\n" +
                            "  style=\"fill:#c6ffcb;stroke:#77ea81;stroke-width:2;fill-opacity:0.9;stroke-opacity:0.99\" />\n" +
                            "  <text x=\""+( 50+ 50*i)+"\" y=\"30\" fill=\"#227c2a\">" + schedule[2][i].name + "</text>";
                }
                else
                {
                    third += "<rect x=\""+ (30 + i*50) +" \" y=\"0\" width=\"50\" height=\"50\"\n" +
                            "  style=\"fill:#CFCFCF;stroke:#4d4d4d;stroke-width:2;fill-opacity:0.2;stroke-opacity:0.15\" />\n" +
                            "  <text x=\""+( 50+ 50*i)+"\" y=\"30\" fill=\"#227c2a\">" + schedule[2][i].name + "</text>";
                }
            }
        }
        third += "  </svg>\n" +
                "\n" +
                "<br>";



        fout.append(first);
        fout.append(second);
        fout.append(third);
        fout.append("\n" +
                "\n" +
                "</body>\n" +
                "</html>\n");
        fout.close();
    }



}
