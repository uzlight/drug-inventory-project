<template>
  <div>
    <div v-if="drugClass">
      <h2>{{ drugClass.class_name }}</h2>
      <h3>Drugs in this class:</h3>
      <ul>
        <li v-for="drug in drugs" :key="drug.id">
          <router-link :to="{ name: 'DrugDetail', params: { id: drug.id } }">{{ drug.drug_name }}</router-link>
        </li>
      </ul>
      <button @click="$router.back()">Back</button>
    </div>
    <div v-else>
      <p>Loading class details...</p>
    </div>
  </div>
</template>

<script>
import { resourceService } from '../services/ResourceService.js';

export default {
  name: 'ClassDetailView',
  props: {
    id: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      drugClass: null,
      drugs: [],
      error: null
    };
  },
  created() {
    this.getClassDetails();
  },
  methods: {
    getClassDetails() {
      resourceService.getClassById(this.id)
        .then(response => {
          this.drugClass = response.data;
          this.getDrugsByClass(this.id);
        })
        .catch(error => {
          console.error("Error fetching class details:", error);
        });
    },
    getDrugsByClass(classId) {
      resourceService.getDrugsByClassId(classId)
        .then(response => {
          this.drugs = response.data;
        })
        .catch(error => {
          console.error("Error fetching drugs for class:", error);
        });
    }
  }
};
</script>

<style scoped>
h2, h3, ul, li {
  font-size: 1.2em;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin-bottom: 10px;
}
button {
  margin-top: 20px;
}
</style>
