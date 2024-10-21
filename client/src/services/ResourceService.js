const choices = [
  {
    id: 1,
    name: "Drugs"
  },
  {
    id: 2,
    name: "Drug Classes"
  }
];

import axios from 'axios'

const resourceService = {
  getChoices() {
    return choices;
  },
  getDrugs() {
    return axios.get('/drug');
  },
  getClasses() {
    return axios.get('/drugClass');  
  },
  getDrugById(drugId) {
    return axios.get(`/drug/${drugId}`);
  },

  getClassById(classId) {
    return axios.get(`/drugClass/${classId}`);
  },
  getDrugsByClassId(classId) {
    return axios.get(`/drug/class/${classId}`);
  },

  createDrug(drug) {
    return axios.post('/drug/createDrug', drug);
  },
  updateDrug(drugId, drug) {
    return axios.put(`/drug/updateDrug`, drug);
  },
  deleteDrug(drugId) {
    return axios.delete(`/drug/${drugId}`);
  },
  
  createDrugClass(drugClass) {
    return axios.post('/drugClass', drugClass);
  },
  updateDrugClass(classId, drugClass) {
    return axios.put(`/drugClass/${classId}`, drugClass);
  },
  deleteDrugClass(classId) {
    return axios.delete(`/drugClass/${classId}`);
  }
};

export { resourceService };