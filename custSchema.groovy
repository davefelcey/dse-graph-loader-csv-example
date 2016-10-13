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
 
