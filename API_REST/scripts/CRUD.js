const axios = require('axios');

const apiTeamUrl = 'http://localhost:8080/api/teams';
const apiPlayerUrl = 'http://localhost:8080/api/players';

async function getTeams() {
  try {

    const response = await axios.get(apiTeamUrl);

    const teams = response.data;

    console.log('Liste des équipes :', teams);
  } catch (error) {

    console.error('Erreur lors de la récupération des équipes :', error.message);
  }
}

async function getPlayer() {
  try {

    const response = await axios.get(apiPlayerUrl);

    const players = response.data;

    console.log('Liste des player :', players);
  } catch (error) {

    console.error('Erreur lors de la récupération des player :', error.message);
  }
}

getTeams();
getPlayer();
