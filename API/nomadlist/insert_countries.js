const cities = require('./cities')
const { db, Countries, Cities } = require('../db')
const Sequelize = require('sequelize')

console.log(`cities = ${cities.length}`)
console.log(cities[ 0 ])
const countries = {}
cities.forEach(c => {
  if (!countries[ c.country_code ]) {
    countries[ c.country_code ] = {
      name: c.country,
      code: c.country_code,
      region: c.region
    }
  }
})



async function task () {
  await Countries.sync({ force: true })

  for (let co in countries) {
    const r = await Countries.create(countries[co])
    console.log(r)
  }
}

async function task1() {
  let c = await Countries.findAll()
  c.forEach(x => console.log(x.dataValues))
}

task1()