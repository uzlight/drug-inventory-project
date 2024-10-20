import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      choices: [
        { id: 1, name: "Drugs" },
        { id: 2, name: "Drug Classes" }
      ],
      drugs: [],
      classes: [],
      currentChoiceId: 1,
      token: currentToken || '',
      user: currentUser || {},
    },
    mutations: {
      SET_CURRENT_CHOICE_ID(state, choiceId) {
        state.currentChoiceId = choiceId;
      },
      SET_CHOICES(state, choices) {
        state.choices = choices;
      },
      SET_DRUGS(state, drugs) {
        state.drugs = drugs;
      },
      SET_CLASSES(state, classes) {
        state.classes = classes;
      },
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
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
    actions: {
      fetchDrugs({ commit }) {
        axios.get('/drug').then(response => {
          commit('SET_DRUGS', response.data);
        }).catch(error => console.error('Error fetching drugs:', error));
      },
      fetchClasses({ commit }) {
        axios.get('/drugClass').then(response => {
          commit('SET_CLASSES', response.data);
        }).catch(error => console.error('Error fetching classes:', error));
      },
      initializeStore({ dispatch }) {
        dispatch('fetchDrugs');
        dispatch('fetchClasses');
      }
    }
  })
  return store;
}
