Gedcom 6.0 xml to Gedcom 5.5 text conversion stylesheets

DESCRIPTION
The stylesheets in this distribution can be used to convert GEDCOM 6.0 xml documents to
Gedcom 5.5 text format.  These stylesheets were written to be used in conjunction
with Gedcom 6.0 files created by the Sourceforge createGedcom project
(a java API for creating GEDCOM 6.0 xml documents).  But the intent is that they
be capable of transforming any GEDCOM 6.0 xml documents to GEDCOM 5.5 text format.

By Ken Stevens
25 Feb 2006
ken.stevens@sympatico.ca

Included in this distribution:
build.xml    - ant build file
schemas/GEDCOM60.* - Gedcom 6.0 schemas
style/*.xslt       - Conversion stylesheets

PREREQUISITES:
The following must be installed before the stylesheets can run
1. Java 1.5 (It may also work with Java 1.4.2; I haven't tried.)
2. Ant (A recent enough version to support the <xslt> tag)
3. To convert large xml documents, you will need to get a copy of saxon version 6.5.5

TO RUN:
1. Edit build.xml and if you are using saxon, set the "saxon.jar" property to 
the location of the saxon.jar file.  If you are not using saxon, do not define the 
saxon.jar property.
2. Edit build.xml and set the "gedcom60.xml" property to the filename you would
like to parse.  A sample input gedcom60.xml is provided with this distribution.
3. Run the program by running "ant" in the folder containing build.xml.

