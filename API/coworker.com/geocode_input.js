const coworks = require('./results')
const fs = require('fs')

console.log(coworks.length)
for (let cw of coworks) {
  fs.appendFileSync(
    './coworks.txt',
    `${cw.name}, ${cw.city} \n`
  )
}