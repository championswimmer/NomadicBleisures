const cities = require('./cities')
const { db, Cities, Countries } = require('../db')
const Sequelize = require('sequelize')

console.log(`cities = ${cities.length}`)
console.log(cities[ 0 ])


async function insert() {
  await Cities.sync({ alter: true })

  for (let c of cities) {
    console.log(c)
    Cities.update({
      tempC: c.temperatureC,
      tempCfeels: c.temperatureC_feels_like,
      image: `http://nomadlist.com${c.image}`,
      score: c.nomad_score,
      weatherEmoji: c.weather_icon
    }, {
      where: {
        slug: c.short_slug
      }
    }).then(x => console.log(x.dataValues))
  }
}

async function check() {
  let c = await Cities.findAll()
  c.forEach(x => console.log(x.dataValues))
}

insert().then(() => check())
