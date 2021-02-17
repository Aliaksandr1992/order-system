# Order system

Order system is service to process wishes and converting that in orders.

# Run

To run this project you need to go inside a root directory, open there terminal and execute the command:

mvn spring-boot:run -Dspring-boot.run.arguments="wishes.filename=wishes.xml"

Yo can use your own xml file with wishes, but it has to have a structure as described below

<wishes>
    <wish>
        <child>
            <first-name>John</first-name>
            <last-name>Smith</last-name>
        </child>
        <text>New car</text>
        <datetime>2018-02-01</datetime>
    </wish>    
</wishes>
