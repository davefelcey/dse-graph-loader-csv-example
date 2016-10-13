# Example of how to load CSV data into DSE Graph using the DSE Graph Loader

This example creates some simple CSV data, a simple DSE Graph schema and shows how the DSE Graph Loader can be used to load this data. The CSV data generator can be found [here](https://github.com/snowindy/csv-test-data-generator) and needs to be downloaded and built to run these tests. You will also need to download and install [DSE Graph](https://academy.datastax.com/downloads/welcome) and [Graph Loader](https://academy.datastax.com/downloads/download-drivers?dxt=DX).

To create your graph you need to first start DSE Graph;
```bash
$DSE_HOME/bin/dse cassandra -k -s -g -f
```
Once DSE has started start the Gremlin console;
```bash
$DSE_HOME/bin/dse gremlin-console
```
In the Gremlin console, create the ExampleGeo graph as follows;
```
system.graph('ExampleCust').create()
:remote config alias g ExampleCust.g
:load <full path>/custSchema.groovy
schema.describe()
```
If you have already created the graph and simply want to erase it and start again you do this as follows;
```
:remote config alias g ExampleCust.g
g.V().drop().iterate()
schema.clear()
system.graph('ExampleCust').drop()
```
Here is the graph schema that will be created by the load command above.
```
// Create the schema with vertices and edges with associated labels and properties
 
// Properties
schema.propertyKey('name').Text().create()
 
schema.propertyKey('gender').Text().create()
schema.propertyKey('age').Int().create()
 
schema.propertyKey('description').Text().create()
schema.propertyKey('cost').Double().create()
 
// Entities
schema.vertexLabel('customer').properties('name','gender','age').create()
schema.vertexLabel('product').properties('name','description').create()
 
// Relationships
schema.edgeLabel('hasProduct').properties('cost').connection('customer','product').create()
 
// Vertex Indexes
 
// Vertex Centric Indexes (VCI) are local to a vertiex and allow their adjacent edges
// and vertices, aswell as their properties to be queries quickly. They are represented
// as a materialised view.
 
// Indexing customers by name
schema.vertexLabel('customer').index('name').materialized().by('name').add()
```
To generate the CSV test data run the create-data.sh script after updating the paths in the script to match the location of the data generator. It will produce 3 CSV files, cust.csv, prod.csv and custprod.csv

You can then run the load.sh script to load the CSV data into the ExampleCust graph. If you preceed the load.sh script with the 'time' command it will output how long the data loading took.
```bash
time ./load.sh
```
