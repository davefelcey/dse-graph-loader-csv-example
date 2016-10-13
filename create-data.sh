#!/bin/bash

NUM_CUST=1000000
NUM_PROD=1000000
GEN_PATH=/Users/davidfelcey/demos/csv-test-data-generator
CWD=`pwd`
OUTPUT_PATH=$CWD

cd $GEN_PATH

# Create verticies

echo "Creating customers..."
node $GEN_PATH/generator.js  "seq(0),name,age,phone" $NUM_CUST $OUTPUT_PATH/cust.csv

echo "Creating products..."
node $GEN_PATH/generator.js "seq(0),pick(Travel|Home|Car|Pet|Contents)" $NUM_PROD $OUTPUT_PATH/prod.csv

# Create edges

echo "Creating customer products..."
node $GEN_PATH/generator.js "seq(0),dollar,seq(0)" $NUM_CUST $OUTPUT_PATH/custprod.csv

cd $CWD


