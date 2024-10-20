import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      choices: [],
      drugs: [],
      classes: [],
      currentChoiceId: 1,
      token: currentToken || '',
      user: currentUser || {},
    },
    mutations: {
      SET_CURRENT_CHOICE_ID(state, choiceId){
        state.currentChoiceId = choiceId;
      },
      SET_CHOICES(state, choices){
        state.choices = choices;
      },
      SET_DRUGS(state, drugs) {
        state.drugs = drugs;
      },
      SET_CLASSES(state, classes) {
        state.classes = classes;
      },
      ADD_DRUG(state, drug) {
        state.drugs.push(drug);
      },
      UPDATE_DRUG(state, updatedDrug) {
        const index = state.drugs.findIndex(drug => drug.id === updatedDrug.id);
        if (index !== -1) {
          state.drugs.splice(index, 1, updatedDrug);
        }
      },
      DELETE_DRUG(state, drugId) {
        state.drugs = state.drugs.filter(drug => drug.id !== drugId);
      },
      ADD_DRUG_CLASS(state, drugClass) {
        state.classes.push(drugClass);
      },
      UPDATE_DRUG_CLASS(state, updatedClass) {
        const index = state.classes.findIndex(cls => cls.id === updatedClass.id);
        if (index !== -1) {
          state.classes.splice(index, 1, updatedClass);
        }
      },
      DELETE_DRUG_CLASS(state, classId) {
        state.classes = state.classes.filter(cls => cls.id !== classId);
      },
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    },
    
  })
  return store;
}