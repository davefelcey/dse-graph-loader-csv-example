// Configures the data loader to create the schema
config preparation: false
config create_schema: false
config load_new: true
config load_edge_threads: 5
config load_vertex_threads: 5
config batch_size: 5000

inputfiledir = '/Users/davidfelcey/demos/csv-test-data-generator/test/'
custInput = File.csv(inputfiledir + 'cust.csv').header('id','name','age','phone')
custInput = custInput.transform { it['age'] = it['age'].toInteger(); it } 
prodInput = File.csv(inputfiledir + 'prod.csv').header('id','name')
custProdInput = File.csv(inputfiledir + 'custprod.csv').header('cust_id','cost','prod_id')
// Remove $ char from cost
custProdInput = custProdInput.transform { it['cost'] = it['cost'].substring(1); it }

// Load customer verticies
load(custInput).asVertices {
  label 'customer'
  key 'id' 
  ignore 'phone'
}

// Load product verticies
load(prodInput).asVertices {
  label 'product'
  key 'id' 
}

// Create customer -> porduct edges 
load(custProdInput).asEdges {
  label 'hasProduct'
  outV 'cust_id', {
    label 'customer'
    key 'id' 
  }
  inV 'prod_id', {
    label 'product'
    key 'id' 
  }
}

