<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:myspace="http://ddd.com/blabla.xsd">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Tariffs</h2>
                <table border="1" align="left">
                    <tr bgcolor="#9acd32">
                        <th>name</th>
                        <th>OperatorName</th>
                        <th>Payroll</th>
                        <th>WithinTheNetwork</th>
                        <th>OutOfTheNetwork</th>
                        <th>OnTheLandlinPhone</th>
                        <th>ConnectinFee</th>
                        <th>Tariffication</th>
                    </tr>
                    <xsl:for-each select="Tariffs/Tariff">
                        <tr>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="OperatorName"/></td>
                            <td><xsl:value-of select="Payroll"/></td>
                            <xsl:for-each select="CallPrice">
                                    <td><xsl:value-of select="myspace:WithinTheNetwork"/></td>
                                    <td><xsl:value-of select="myspace:OutOfTheNetwork"/></td>
                                    <td><xsl:value-of select="myspace:OnTheLandlinPhone"/></td>
                            </xsl:for-each>
                            <xsl:for-each select="Parameters">

                                    <td><xsl:value-of select="ConnectinFee"/></td>
                                     <td><xsl:value-of select="Tariffication"/></td>

                            </xsl:for-each>
                        </tr>

                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>