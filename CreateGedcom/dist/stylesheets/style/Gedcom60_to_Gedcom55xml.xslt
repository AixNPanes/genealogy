<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" />
<xsl:template match="GEDCOM">
<GEDCOM>
	<xsl:apply-templates select="HeaderRec"/>
	<xsl:apply-templates select="SourceRec"/>
	<xsl:apply-templates select="IndividualRec"/>
	<xsl:apply-templates select="FamilyRec"/>
    <xsl:apply-templates select="//ContactRec[@Id=current()/HeaderRec/Submitter/Link/@Ref]" mode="submitter"/>
	<!-- GroupRec not supported in Gedcom 5.5 -->
</GEDCOM>
</xsl:template>

<xsl:template match="HeaderRec">
<HEAD>
<xsl:apply-templates select="FileCreation"/>
<xsl:apply-templates select="Submitter"/>
</HEAD>
</xsl:template>

<xsl:template match="FileCreation">
  <DATE value="{@Date}">
    <TIME value="{@Time}"/>
  </DATE>
<xsl:apply-templates select="Product"/>
</xsl:template>

<xsl:template match="Submitter">
  <SUBM>
    <xsl:attribute name="value">@<xsl:value-of select="Link/@Ref"/>@</xsl:attribute>
  </SUBM>
</xsl:template>

<xsl:template match="Product">
  <SOUR value="{ProductId}">
    <VERS value="{Version}"/>
    <NAME value="{Name}"/>
    <xsl:apply-templates select="//ContactRec[@Id=current()/Supplier/Link/@Ref]" mode="corp"/>
  </SOUR>
</xsl:template>

 
<xsl:template match="ContactRec" mode="corp">
  <CORP value="{Name}">
    <xsl:apply-templates select="MailAddress|Phone"/>
    <!-- TODO: Email and URI -->
  </CORP>
</xsl:template>

  <xsl:template match="ContactRec" mode="submitter">
    <SUBM Id="{@Id}">
      <NAME value="{Name}"/>
      <xsl:apply-templates select="MailAddress|Phone"/>
      <!-- TODO: Email and URI -->
    </SUBM>
  </xsl:template>

<xsl:template match="Phone">
  <PHON value="{.}"/>
</xsl:template>

<xsl:template match="ContactRec" mode="indi">
  <RESI>
    <xsl:apply-templates select="MailAddress" mode="asPlace"/>
  </RESI>
</xsl:template>

<xsl:template match="MailAddress" mode="asAddr">
      <ADDR>
      <xsl:apply-templates select="AddrLine"/>
      </ADDR>
</xsl:template>

<xsl:template match="AddrLine">
  <xsl:apply-templates select="PlacePart"/>
</xsl:template>

<xsl:template match="MailAddress" mode="asPlace">
  <xsl:call-template name="PlacePartsToPlace"/>
</xsl:template>

<xsl:template match="PlacePart">
  <xsl:choose>
     <xsl:when test="@Level='1'">
        <CTRY value="{.}"/>
     </xsl:when>
     <xsl:when test="@Level='2'">
        <STAE value="{.}"/>
     </xsl:when>
     <xsl:when test="@Level='4'">
        <CITY value="{.}"/>
     </xsl:when>
     <xsl:when test="@Level='5'">
        <POST value="{.}"/>
     </xsl:when>
     <xsl:when test="@Level='6'">
        <ADR1 value="{.}"/>
     </xsl:when>
  </xsl:choose>
</xsl:template>
<xsl:template match="SourceRec">
<SOUR Id="{@Id}">
  <TITL value="{Title}"/>
  <xsl:apply-templates select="Note"/>
</SOUR>
</xsl:template>
<xsl:template match="IndividualRec">
<INDI Id="{@Id}">
<xsl:apply-templates select="IndivName"/>
  <SEX value="{Gender}"/>
<xsl:apply-templates select="//EventRec[Participant/Link/@Ref=current()/@Id]" mode="indi"/>
<xsl:apply-templates select="//ContactRec[SameIndiv/Link/@Ref=current()/@Id]" mode="indi"/>
<xsl:if test="PersInfo[@Type='occupation']">
  <OCCU value="{PersInfo[@Type='occupation']/Information}"/>
</xsl:if>
<xsl:apply-templates select="//FamilyRec[Child/Link/@Ref=current()/@Id]" mode="child"/>
<xsl:apply-templates select="//FamilyRec[(HusbFath|WifeMoth)/Link/@Ref=current()/@Id]" mode="spouse"/>
<xsl:apply-templates select="//SourceRec[@Id=current()/Evidence/Citation/Link/@Ref]" mode="citation"/>
<xsl:apply-templates select="Note"/>
<xsl:apply-templates select="Changed"/>
</INDI>

</xsl:template>

<xsl:template match="Changed">
<CHAN>
  <DATE value="{@Date}">
    <TIME value="{@Time}"/>
  </DATE>
</CHAN>
</xsl:template>

<xsl:template match="FamilyRec" mode="child">
<FAMC>
<xsl:attribute name="value">@<xsl:value-of select="@Id"/>@</xsl:attribute>
</FAMC>
</xsl:template>
<xsl:template match="FamilyRec" mode="spouse">
<FAMS>
<xsl:attribute name="value">@<xsl:value-of select="@Id"/>@</xsl:attribute>
</FAMS>
</xsl:template>
<xsl:template match="SourceRec" mode="citation">
<SOUR>
<xsl:attribute name="value">@<xsl:value-of select="@Id"/>@</xsl:attribute>
</SOUR>
</xsl:template>
<xsl:template match="IndivName">
  <NAME value="{NamePart[@Level='3']} /{NamePart[@Level='1']}/">
    <xsl:apply-templates select="NamePart"/>
  </NAME>
</xsl:template>

<xsl:template match="NamePart">
  <xsl:choose>
  <xsl:when test="@Level='3'">
    <GIVN value="{.}"/>
  </xsl:when>
  <xsl:when test="@Level='1'">
    <SURN value="{.}"/>
    </xsl:when>
  </xsl:choose>
</xsl:template>


<xsl:template match="EventRec" mode="indi">
  <xsl:choose>
  <xsl:when test="@VitalType='birth'">
  <BIRT>
    <DATE value="{Date}"/>
    <xsl:apply-templates select="Place/PlaceName"/>
  </BIRT>
  </xsl:when>
  <xsl:when test="@VitalType='death'">
  <DEAT>
    <DATE value="{Date}"/>
  </DEAT>
  </xsl:when>
<!-- Assume occupation records are listed as event records of type "occupation" -->
  <xsl:when test="@Type='occupation'">
  <OCCU value="{Note}"/>
  </xsl:when>
  </xsl:choose>
</xsl:template>
<xsl:template match="PlaceName">
    <xsl:call-template name="PlacePartsToPlace"/>
</xsl:template>
<xsl:template name="PlacePartsToPlace">
    <PLAC>
<xsl:attribute name="value"><xsl:if test=".//PlacePart[@Level='6']"><xsl:value-of select=".//PlacePart[@Level='6']"/>, </xsl:if><xsl:if test=".//PlacePart[@Level='4']"><xsl:value-of select=".//PlacePart[@Level='4']"/>, </xsl:if><xsl:if test=".//PlacePart[@Level='2']"><xsl:value-of select=".//PlacePart[@Level='2']"/>, </xsl:if><xsl:if test=".//PlacePart[@Level='1']"><xsl:value-of select=".//PlacePart[@Level='1']"/></xsl:if></xsl:attribute>   
    </PLAC>
</xsl:template>
<xsl:template match="FamilyRec">
<FAM Id="{@Id}">
<xsl:apply-templates select="HusbFath"/>
<xsl:apply-templates select="WifeMoth"/>
<xsl:apply-templates select="BasedOn"/>
<xsl:apply-templates select="Child"/>
</FAM>
</xsl:template>
<xsl:template match="HusbFath">
<HUSB>
<xsl:attribute name="value">@<xsl:value-of select="Link/@Ref"/>@</xsl:attribute>
</HUSB>
</xsl:template>
<xsl:template match="WifeMoth">
<WIFE>
<xsl:attribute name="value">@<xsl:value-of select="Link/@Ref"/>@</xsl:attribute>
</WIFE>
</xsl:template>
<xsl:template match="Child">
<CHIL>
<xsl:attribute name="value">@<xsl:value-of select="Link/@Ref"/>@</xsl:attribute>
</CHIL>
</xsl:template>
<xsl:template match="BasedOn">
<MARR>
<xsl:apply-templates select="//EventRec[@Id=current()/Link/@Ref]" mode="fam"/>
</MARR>
</xsl:template>
<xsl:template match="EventRec" mode="fam">
<DATE value="{Date}"/>
</xsl:template>

<xsl:template match="Note">
	<NOTE>
	<xsl:call-template name="wrap-string">
		<xsl:with-param name="str" select="."/>
		<!-- Max wrap-col allowed according to GEDCOM 5.5 standard is 248 -->
		<xsl:with-param name="wrap-col" select="128"/>
		<xsl:with-param name="break-tag" select="'CONT'"/>
		<xsl:with-param name="break-attribute" select="'value'"/>
	</xsl:call-template>
	</NOTE>
</xsl:template>
<xsl:template name="wrap-string">
    <xsl:param name="str" />
    <xsl:param name="wrap-col" />
    <xsl:param name="break-tag" />
    <xsl:param name="break-attribute" />
    <xsl:param name="pos" select="0" />
    
    
    <xsl:choose>
        <xsl:when test="contains( $str, ' ' )">
            <xsl:variable name="before" select="substring-before( $str, ' ' )" />
            <xsl:variable name="pos-now" select="$pos + string-length( $before )" /> 
            <xsl:choose>
                <xsl:when test="($pos = 0) and ($pos-now != 0)" >
                    <xsl:text disable-output-escaping = "yes">&lt;</xsl:text>
					<xsl:value-of select="$break-tag"/>
					<xsl:text> </xsl:text>
					<xsl:value-of select="$break-attribute"/>
				    <xsl:text>="</xsl:text>
                </xsl:when>
                <xsl:when test="floor( $pos div $wrap-col ) != floor( $pos-now div $wrap-col )">
					<xsl:text disable-output-escaping = "yes">"/&gt;&#xa;</xsl:text>
                	<xsl:text disable-output-escaping = "yes">&lt;</xsl:text>
    				<xsl:value-of select="$break-tag"/>
    				<xsl:text> </xsl:text>
    				<xsl:value-of select="$break-attribute"/>
    				<xsl:text>="</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text> </xsl:text>
                </xsl:otherwise>
            </xsl:choose> 
            <xsl:value-of select="$before" /> 
            <xsl:call-template name="wrap-string">
                <xsl:with-param name="str" select="substring-after( $str, ' ' )" />
                <xsl:with-param name="wrap-col" select="$wrap-col" />
                <xsl:with-param name="break-tag" select="$break-tag" />
                <xsl:with-param name="break-attribute" select="$break-attribute" />
                <xsl:with-param name="pos" select="$pos-now" />
            </xsl:call-template>
        </xsl:when>
        <xsl:otherwise>
            <xsl:if test="$pos &gt; 0"><xsl:text> </xsl:text></xsl:if>
            <xsl:value-of select="$str" />            
          <xsl:if test="$pos &gt; 0"><xsl:text disable-output-escaping = "yes">"/&gt;&#xa;</xsl:text></xsl:if>
        </xsl:otherwise>
    </xsl:choose>
</xsl:template>  


</xsl:stylesheet>
