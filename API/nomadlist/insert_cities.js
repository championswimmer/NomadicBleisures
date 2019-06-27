const cities = require('./cities')
const { db, Cities, Countries } = require('../db')
const Sequelize = require('sequelize')

console.log(`cities = ${cities.length}`)
console.log(cities[ 0 ])


async function insert() {
  await Cities.sync({ })

  for (let c of cities) {
    console.log(c)
    Cities.create({
      name: c.name,
      slug: c.short_slug,
      countryId: c.country_code,
      description: c.descriptionFromReview,
      aqi:  c.air_quality_now,
      internetMbps: c.internet_speed,
      latitude: c.latitude,
      longitude: c.longitude,
      population: c.population,
      safetyLevel:  c.safety_level,
      userBeen: c.users_been_count,
      livingCost: c.short_term_cost_in_usd
    }).then(c => console.log(c.dataValues))
  }
}

async function check () {
  let c = await Cities.findAll()
  c.forEach(x => console.log(x.dataValues))
}

insert().then(() => check())