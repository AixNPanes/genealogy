set CP=C:\EclipseProjects\Genealogy\OpenJPA-1.2.2\lib\serp-1.13.1.jar
set CP=%CP%;C:\EclipseProjects\Genealogy\OpenJPA-1.2.2\lib\openjpa-1.2.2.jar
set CP=%CP%;C:\EclipseProjects\Genealogy\apache-openjpa-1.2.1\lib\commons-lang-2.1.jar
set CP=%CP%;C:\EclipseProjects\Genealogy\apache-openjpa-1.2.1\lib\geronimo-jta_1.1_spec-1.1.jar
set CP=%CP%;C:\EclipseProjects\Genealogy\XmlOrigToSql\src\
set CP=%CP%;C:\EclipseProjects\Genealogy\XmlOrigToSql\src\META-INF\
set CP=%CP%;C:\EclipseProjects\Genealogy\XmlOrigToSql\src\META-INF\persistence.xml
java -cp %CP% org.apache.openjpa.enhance.PCEnhancer -p src\META-INF\persistence.xml 