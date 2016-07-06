<?xml version="1.0" encoding="UTF-8"?>
   <xsl:stylesheet version="1.0"
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output method="text"/>
   <xsl:param name="indent-increment" select="'  '" />
   
   <xsl:template match="GEDCOM">
   <xsl:apply-templates select="*"/>
0 TRLR</xsl:template>
   
   <xsl:template match="*">
      <xsl:param name="indent" select="'&#xA;'"/>
      <xsl:param name="depth" select="0" />
      <xsl:value-of select="$indent"/>
      <xsl:value-of select="$depth"/>
      <xsl:value-of select="' '"/>
      <xsl:if test="@Id">@<xsl:value-of select="@Id"/>@<xsl:value-of select="' '"/>
      </xsl:if>
		<xsl:value-of select="name(.)"/>
	    <xsl:value-of select="' '"/>
      <xsl:value-of disable-output-escaping="yes" select="@value"/>
        <xsl:apply-templates>
          <xsl:with-param name="indent"
               select="concat($indent, $indent-increment)"/>
          <xsl:with-param name="depth" select="$depth+1"/>
        </xsl:apply-templates>
   </xsl:template>
   <!-- WARNING: this is dangerous. Handle with care -->
   <xsl:template match="text()[normalize-space(.)='']"/>
</xsl:stylesheet>
