/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


<?xml version="1.0" encoding="UTF-8"?>
<!-- Created with Jaspersoft Studio version 6.12.0.final using JasperReports Library version 6.12.1-ac0eebdb29e4c0985457bab279a6db744d661530  -->
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="SounCheckInvoice" pageWidth="595" pageHeight="842" columnWidth="555" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20" uuid="399cb31e-de5b-4a81-9bff-e128c1413bf4">
  
    
   
    <field name="datecreated" class="java.sql.Timestamp">
        <property name="com.jaspersoft.studio.field.label" value="datecreated"/>
        <property name="com.jaspersoft.studio.field.tree.path" value="invoice"/>
    </field>
    <field name="masteringcost" class="java.lang.Double">
        <property name="com.jaspersoft.studio.field.label" value="masteringcost"/>
        <property name="com.jaspersoft.studio.field.tree.path" value="invoice"/>
    </field>
    <field name="mixingcost" class="java.lang.Double">
        <property name="com.jaspersoft.studio.field.label" value="mixingcost"/>
        <property name="com.jaspersoft.studio.field.tree.path" value="invoice"/>
    </field>
    <field name="studiotimecost" class="java.lang.Double">
        <property name="com.jaspersoft.studio.field.label" value="studiotimecost"/>
        <property name="com.jaspersoft.studio.field.tree.path" value="invoice"/>
    </field>
    <field name="timeinhr" class="java.lang.Integer">
        <property name="com.jaspersoft.studio.field.label" value="timeinhr"/>
        <property name="com.jaspersoft.studio.field.tree.path" value="invoice"/>
    </field>
    <field name="totalcost" class="java.lang.Double">
        <property name="com.jaspersoft.studio.field.label" value="totalcost"/>
        <property name="com.jaspersoft.studio.field.tree.path" value="invoice"/>
    </field>
    <field name="invoiceno" class="java.lang.String">
        <property name="com.jaspersoft.studio.field.label" value="invoiceno"/>
        <property name="com.jaspersoft.studio.field.tree.path" value="invoice"/>
    </field>
    <field name="costofintruments" class="java.lang.Double">
        <property name="com.jaspersoft.studio.field.label" value="costofintruments"/>
        <property name="com.jaspersoft.studio.field.tree.path" value="invoice"/>
    </field>
    <field name="totalstudicost" class="java.lang.Double">
        <property name="com.jaspersoft.studio.field.label" value="totalstudicost"/>
        <property name="com.jaspersoft.studio.field.tree.path" value="invoice"/>
    </field>
    <field name="country" class="java.lang.String">
        <property name="com.jaspersoft.studio.field.label" value="country"/>
        <property name="com.jaspersoft.studio.field.tree.path" value="project"/>
    </field>
    <background>
        <band splitType="Stretch"/>
    </background>
    <title>
        <band splitType="Stretch"/>
    </title>
    <pageHeader>
        <band height="10" splitType="Stretch"/>
    </pageHeader>
    <columnHeader>
        <band splitType="Stretch"/>
    </columnHeader>
    <detail>
        <band height="570" splitType="Stretch">
            <textField>
                <reportElement x="440" y="90" width="124" height="40" uuid="b0b37539-dc80-4045-bfc9-f638f1a09267">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="995c46fa-fc0a-4a2f-88f4-e0b95252b4a7"/>
                </reportElement>
                <textElement textAlignment="Left" verticalAlignment="Middle">
                    <font size="14"/>
                </textElement>
                <textFieldExpression><![CDATA[$F{datecreated}]]></textFieldExpression>
            </textField>
            <textField>
                <reportElement x="270" y="222" width="160" height="30" uuid="3a2d9065-4607-4d9f-b2b5-aff9df2268c8">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="35209e90-5d26-4843-b41d-c7af579f612c"/>
                </reportElement>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <textFieldExpression><![CDATA[$F{masteringcost}]]></textFieldExpression>
            </textField>
            <textField>
                <reportElement x="271" y="260" width="159" height="30" uuid="129e7302-2b97-4471-bbff-6e4f9b871b59">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="32440c5f-3d39-4eef-a863-bc111f3788d8"/>
                </reportElement>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <textFieldExpression><![CDATA[$F{mixingcost}]]></textFieldExpression>
            </textField>
            <textField>
                <reportElement x="270" y="192" width="160" height="30" uuid="18a28adb-b2e9-47d0-a1e1-2c6a5f44aa21">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="d04edfe4-474f-4d69-a3df-94b653c61e96"/>
                </reportElement>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <textFieldExpression><![CDATA[$F{studiotimecost}]]></textFieldExpression>
            </textField>
            <textField>
                <reportElement x="141" y="192" width="129" height="30" uuid="b749f184-d640-4fbe-b927-e42adc8b7eb7">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="da071458-2fe3-4e79-b4ed-ad00dd0847c0"/>
                </reportElement>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <textFieldExpression><![CDATA[$F{timeinhr}]]></textFieldExpression>
            </textField>
            <textField>
                <reportElement x="470" y="60" width="95" height="40" uuid="14fb08a8-1567-4c55-805a-199bf9d2af36">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="1be77f17-8be9-4997-b1fc-651e36f4c7d6"/>
                </reportElement>
                <textElement textAlignment="Left" verticalAlignment="Middle">
                    <font size="14"/>
                </textElement>
                <textFieldExpression><![CDATA[$F{invoiceno}]]></textFieldExpression>
            </textField>
            <textField>
                <reportElement x="270" y="293" width="161" height="30" uuid="fd5553e1-d9ef-4a25-a4c8-add1b344f142">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="9585d904-c5dc-49f9-a38e-e8da4648d08a"/>
                </reportElement>
                <textElement textAlignment="Center" verticalAlignment="Middle"/>
                <textFieldExpression><![CDATA[$F{costofintruments}]]></textFieldExpression>
            </textField>
            <textField>
                <reportElement x="61" y="100" width="90" height="30" uuid="037bd3fe-2c8e-4990-8c38-452c6a272013">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="d967e05b-39be-4e91-b705-3ed04f04d52d"/>
                </reportElement>
                <textElement textAlignment="Left">
                    <font size="14"/>
                </textElement>
                <textFieldExpression><![CDATA[$F{country}]]></textFieldExpression>
            </textField>
            <staticText>
                <reportElement x="380" y="90" width="60" height="40" uuid="1bf58eac-74a9-43d4-885a-867fe169116e">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="995c46fa-fc0a-4a2f-88f4-e0b95252b4a7"/>
                </reportElement>
                <textElement textAlignment="Right" verticalAlignment="Middle">
                    <font size="14"/>
                </textElement>
                <text><![CDATA[Date:]]></text>
            </staticText>
            <staticText>
                <reportElement x="10" y="220" width="114" height="30" uuid="0c5e3867-f2f8-4873-8ee3-e6b33b816ac6">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="35209e90-5d26-4843-b41d-c7af579f612c"/>
                </reportElement>
                <textElement textAlignment="Right" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <text><![CDATA[Mastering cost]]></text>
            </staticText>
            <staticText>
                <reportElement x="9" y="260" width="115" height="30" uuid="d29e183d-e189-4fab-9ca9-01988d54d086">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="32440c5f-3d39-4eef-a863-bc111f3788d8"/>
                </reportElement>
                <textElement textAlignment="Right" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <text><![CDATA[mixingcost]]></text>
            </staticText>
            <staticText>
                <reportElement x="10" y="192" width="114" height="30" uuid="650a1682-092a-410a-8f94-3996af3d08ea">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="d04edfe4-474f-4d69-a3df-94b653c61e96"/>
                </reportElement>
                <textElement textAlignment="Right" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <text><![CDATA[Studio time cost]]></text>
            </staticText>
            <staticText>
                <reportElement x="430" y="323" width="61" height="30" uuid="01f345a4-0e45-49d1-ad67-462ba2f9b33e">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="44dc7d2f-c025-446f-9887-474e6d2455c8"/>
                </reportElement>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <text><![CDATA[Sub Total =]]></text>
            </staticText>
            <staticText>
                <reportElement x="391" y="60" width="79" height="40" uuid="6d1599c9-daf2-410a-8b5a-3e13a5047b38">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="1be77f17-8be9-4997-b1fc-651e36f4c7d6"/>
                </reportElement>
                <textElement textAlignment="Right" verticalAlignment="Middle">
                    <font size="14"/>
                </textElement>
                <text><![CDATA[Invoice # :]]></text>
            </staticText>
            <staticText>
                <reportElement x="9" y="293" width="116" height="30" uuid="6c21a69b-fc30-4f1e-ac2b-43c7390d660b">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="9585d904-c5dc-49f9-a38e-e8da4648d08a"/>
                </reportElement>
                <textElement textAlignment="Right" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <text><![CDATA[Cost of instruments]]></text>
            </staticText>
            <staticText>
                <reportElement x="0" y="100" width="61" height="30" uuid="54097a17-cc0c-4e5d-9810-6920644460c4">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="d967e05b-39be-4e91-b705-3ed04f04d52d"/>
                </reportElement>
                <textElement textAlignment="Right">
                    <font size="14"/>
                </textElement>
                <text><![CDATA[country:]]></text>
            </staticText>
            <staticText>
                <reportElement x="10" y="82" width="100" height="30" uuid="62c91a0b-8f81-47b2-990e-babec553d6d5"/>
                <textElement textAlignment="Left">
                    <font size="14"/>
                </textElement>
                <text><![CDATA[Invoice to:]]></text>
            </staticText>
            <staticText>
                <reportElement x="11" y="160" width="129" height="30" backcolor="#3B1C1B" uuid="24cc634e-4e2d-42a7-bb1d-0503a630bd5f"/>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12" isBold="true"/>
                </textElement>
                <text><![CDATA[Description
                    ]]></text>
            </staticText>
            <staticText>
                <reportElement x="141" y="160" width="134" height="30" backcolor="#3B1C1B" uuid="8f48891b-13e2-435d-a3c3-e303ec4b48b8"/>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12" isBold="true"/>
                </textElement>
                <text><![CDATA[No of hours]]></text>
            </staticText>
            <staticText>
                <reportElement x="270" y="160" width="160" height="30" backcolor="#3B1C1B" uuid="c0e4c772-7f56-4089-8636-6f48a38d82b1"/>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12" isBold="true"/>
                </textElement>
                <text><![CDATA[cost (GHc)]]></text>
            </staticText>
            <staticText>
                <reportElement x="431" y="160" width="122" height="30" backcolor="#3B1C1B" uuid="2ed21cfb-c411-4bcd-8ff0-42f61706ae22"/>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12" isBold="true"/>
                </textElement>
                <text><![CDATA[Total cost(GHc)]]></text>
            </staticText>
            <line>
                <reportElement x="9" y="190" width="545" height="1" uuid="95e95377-0903-4381-a2a1-e308ecea73eb"/>
            </line>
            <textField>
                <reportElement x="432" y="220" width="121" height="30" uuid="330cddbd-93e9-4bc7-b8e1-48cd82c57ced">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="35209e90-5d26-4843-b41d-c7af579f612c"/>
                </reportElement>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <textFieldExpression><![CDATA[$F{masteringcost}]]></textFieldExpression>
            </textField>
            <textField>
                <reportElement x="431" y="260" width="124" height="30" uuid="cda526e3-d8a8-427b-9b2c-367dcc1fe94e">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="32440c5f-3d39-4eef-a863-bc111f3788d8"/>
                </reportElement>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <textFieldExpression><![CDATA[$F{mixingcost}]]></textFieldExpression>
            </textField>
            <textField>
                <reportElement x="431" y="293" width="121" height="30" uuid="c660418b-1624-4351-bc74-682854d3e7f2">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="9585d904-c5dc-49f9-a38e-e8da4648d08a"/>
                </reportElement>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <textFieldExpression><![CDATA[$F{costofintruments}]]></textFieldExpression>
            </textField>
            <line>
                <reportElement x="10" y="159" width="545" height="1" uuid="132ab8ef-d44c-4048-ae09-0a4221b63598"/>
            </line>
            <line>
                <reportElement x="10" y="160" width="1" height="165" uuid="a606a54d-fdf3-4037-aae9-5aeed39287dd"/>
            </line>
            <line>
                <reportElement x="554" y="160" width="1" height="165" uuid="17e7a361-a6be-4b99-98fc-4d356dd0df19"/>
            </line>
            <line>
                <reportElement x="9" y="325" width="545" height="1" uuid="4e4cff8e-795e-4440-8005-0a786c97bb8e"/>
            </line>
            <line>
                <reportElement x="140" y="160" width="1" height="165" uuid="8e9eb962-144f-4548-b04e-395a918ef18c"/>
            </line>
            <line>
                <reportElement x="270" y="160" width="1" height="165" uuid="5e330a7e-d98c-4522-bf3e-ff5ae57e36d3"/>
            </line>
            <line>
                <reportElement x="430" y="160" width="1" height="165" uuid="840ebc2a-10cd-4e92-9230-823eafc0d27e"/>
            </line>
            <line>
                <reportElement x="430" y="326" width="1" height="25" uuid="e31b39f9-e1e4-4974-a4ca-51cfed85693b"/>
            </line>
            <line>
                <reportElement x="430" y="351" width="125" height="1" uuid="ad80db31-6bb0-4d7d-9f1b-83ec9c7a8b76"/>
            </line>
            <line>
                <reportElement x="554" y="326" width="1" height="25" uuid="7e603384-779b-4c0a-8a15-fdc50ad4a903"/>
            </line>
            <textField>
                <reportElement x="491" y="323" width="61" height="30" uuid="2e3a39ec-106b-40c0-b6dc-3d3c1ec167ca">
                    <property name="com.jaspersoft.studio.spreadsheet.connectionID" value="44dc7d2f-c025-446f-9887-474e6d2455c8"/>
                </reportElement>
                <textElement verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <textFieldExpression><![CDATA[$F{totalcost}]]></textFieldExpression>
            </textField>
            <textField>
                <reportElement x="430" y="192" width="122" height="30" uuid="724c16b0-32d2-4ecd-bd4f-11ccafd4e4a4"/>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font size="12"/>
                </textElement>
                <textFieldExpression><![CDATA[$F{totalstudicost}]]></textFieldExpression>
            </textField>
            <staticText>
                <reportElement x="420" y="20" width="100" height="30" forecolor="#5C043A" uuid="d058993f-e4ac-4dff-b108-1d8a99e0b55a"/>
                <textElement textAlignment="Center" verticalAlignment="Middle">
                    <font fontName="Berlin Sans FB Demi" size="15"/>
                </textElement>
                <text><![CDATA[INVOICE]]></text>
            </staticText>
            <staticText>
                <reportElement x="10" y="386" width="100" height="30" backcolor="rgba(255, 250, 250, 0.65882355)" uuid="9439f5e3-e42d-42ec-b141-923236861a7c"/>
                <textElement textAlignment="Center" verticalAlignment="Middle"/>
                <text><![CDATA[Payment Method]]></text>
            </staticText>
            <staticText>
                <reportElement x="432" y="388" width="68" height="30" uuid="d8d28a72-4f24-4905-845b-22c316847a43"/>
                <textElement textAlignment="Left" verticalAlignment="Middle"/>
                <text><![CDATA[Grand Total  ]]></text>
            </staticText>
            <staticText>
                <reportElement x="431" y="360" width="100" height="30" uuid="bec8ed81-cca8-485f-9edd-910df61272dd"/>
                <textElement>
                    <font size="12"/>
                </textElement>
                <text><![CDATA[Tax(0%) = 0.00]]></text>
            </staticText>
            <textField>
                <reportElement x="500" y="388" width="62" height="30" uuid="2a1eb66b-9f25-4852-a048-659e8c81743e"/>
                <textElement verticalAlignment="Middle"/>
                <textFieldExpression><![CDATA[$F{totalcost}]]></textFieldExpression>
            </textField>
            <staticText>
                <reportElement x="438" y="464" width="114" height="30" uuid="257f740a-2b6b-4f01-a1a8-0ba1bd15d8fa"/>
                <textElement>
                    <font fontName="Arial Narrow"/>
                </textElement>
                <text><![CDATA[MR SOLOMON ADIKO]]></text>
            </staticText>
            <staticText>
                <reportElement x="0" y="430" width="150" height="70" forecolor="#0F0101" uuid="d1a29d1c-d5f2-4e7c-8d61-8e1574272f46"/>
                <textElement textAlignment="Center">
                    <font size="10" isItalic="false"/>
                </textElement>
                <text><![CDATA[
                    Account name: Uncle Ato
                    Account Number :1100113786
                    Ghana Commercial bank]]></text>
            </staticText>
            <staticText>
                <reportElement x="30" y="420" width="100" height="30" uuid="ba7af286-eda6-47e9-8a64-7e2c9d806d10"/>
                <textElement>
                    <font size="12" isBold="true"/>
                </textElement>
                <text><![CDATA[Bank Account]]></text>
            </staticText>
            <staticText>
                <reportElement x="24" y="490" width="100" height="30" uuid="961c1d1e-ca44-459b-8e21-20910e529f84"/>
                <textElement>
                    <font size="12" isBold="true"/>
                </textElement>
                <text><![CDATA[Mobile Money]]></text>
            </staticText>
            <staticText>
                <reportElement x="32" y="510" width="104" height="30" uuid="c4955300-bf44-4e33-a851-6eabbc867bc4"/>
                <text><![CDATA[0544793757]]></text>
            </staticText>
            <staticText>
                <reportElement x="464" y="474" width="100" height="30" uuid="8c3e0b5c-ad4a-4d8b-9afa-7ad64696c8de"/>
                <textElement>
                    <font size="9"/>
                </textElement>
                <text><![CDATA[Studio manager]]></text>
            </staticText>
            <image>
                <reportElement x="170" y="-5" width="101" height="80" uuid="b1b1fcea-5384-428e-b8a1-90bd47d4bce0"/>
                <imageExpression><![CDATA["C:/Users/Ish/Documents/SoundCheckRecords/src/main/resources/static/global_assets/images/SCR.jpg"]]></imageExpression>
            </image>
            <staticText>
                <reportElement x="9" y="-10" width="150" height="92" uuid="a1d77fe0-326e-4480-91d2-9571c5d1c23d"/>
                <textElement textAlignment="Center">
                    <font size="9"/>
                </textElement>
                <text><![CDATA[SoundCheck Record
                    P. O.Box GHF43A
                    Nungua Opp. GCB Training sch.
                    Accra
                    Tel: 0544793757
                    e-mail: info@soucheckrecords.com
                    website: www.soundcheck.com.gh]]></text>
            </staticText>
            <image>
                <reportElement x="464" y="433" width="50" height="50" uuid="46d500c7-f738-417a-ab54-d9e286ffdd6c"/>
                <imageExpression><![CDATA["C:/Users/Ish/Documents/SoundCheckRecords/src/main/resources/static/global_assets/images/signature.png"]]></imageExpression>
            </image>
            <staticText>
                <reportElement x="7" y="540" width="192" height="30" uuid="8e4c1681-fd51-466f-bbb2-c98c5827c7ad"/>
                <textElement>
                    <font fontName="Harlow Solid Italic" size="14"/>
                </textElement>
                <text><![CDATA[Thank you for business with us!!]]></text>
            </staticText>
            <line>
                <reportElement x="7" y="530" width="151" height="1" uuid="99f8f737-7100-4a33-813f-a3052e2fb56e"/>
            </line>
        </band>
    </detail>
    <columnFooter>
        <band height="45" splitType="Stretch"/>
    </columnFooter>
    <pageFooter>
        <band height="54" splitType="Stretch"/>
    </pageFooter>
    <summary>
        <band height="42" splitType="Stretch"/>
    </summary>
</jasperReport>
