Dropping graph to reset;

:remote config alias g ExampleCust.g
g.V().drop().iterate()
schema.clear()
system.graph('ExampleCust').drop()

Creating the graph prior to loading;

system.graph('ExampleCust').create()
:remote config alias g ExampleCust.g
:load <full path>/custSchema.groovy
schema.describe()

