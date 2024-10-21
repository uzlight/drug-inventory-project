<template>
    <div>
      <div>
        <h3>Create a New Drug</h3>
        <input v-model="newDrugName" placeholder="Drug Name" />
        <input v-model="newDrugCost" placeholder="Cost" type="number" />
        <!-- <input v-model="newDrugClass" placeholder="Class Name" /> -->
        <button @click="createDrug">Create Drug</button>
      </div>
        <h3>Drug List</h3>
      <ul>
        <li v-for="drug in drugs" :key="drug.id">
          {{ drug.name }} - ${{ drug.cost }}
          <div>
            <input v-model="drug.name" placeholder="Drug Name" />
            <input v-model="drug.cost" placeholder="Cost" />
            <button @click="updateDrug(drug.id)">Update</button>
            <button @click="deleteDrug(drug.id)">Delete</button>
          </div>
        </li>
      </ul>
    </div>
  </template>
  

<script>
import { resourceService } from '../services/ResourceService.js';

export default {
  data() {
    return {
      newDrugName: '',
      newDrugCost: 0,
      drugs: [],
      drugId: null,
      classId: null,
    //   newDrugClass: '',
    };
  },
  computed: {
    isAdmin() {
      return (
        this.$store.state.user &&
        this.$store.state.user.role &&
        this.$store.state.user.role.includes("ROLE_ADMIN")
      );  
    }
  },
  methods: {
    createDrug() {
      const drug = {
        name: this.newDrugName,
        cost: this.newDrugCost,
        isAvailable: true, 
        classId: this.classId,
           // class: this.newDrugClass,
      };
      resourceService.createDrug(drug)
        .then(response => {
          this.drugs.push(response.data); 
          this.newDrugName = '';
          this.newDrugCost = 0;
        //   this.newDrugClass = '';
        })
        .catch(error => {
          console.error("Error creating drug:", error);
        });
    },
    updateDrug(drugId) {
      const drug = this.drugs.find(d => d.id === drugId);
      resourceService.updateDrug(drugId, drug)
        .then(response => {
          console.log("Drug updated successfully:", response.data);
        })
        .catch(error => {
          console.error("Error updating drug:", error);
        });
    },
    deleteDrug(drugId) {
      resourceService.deleteDrug(drugId)
        .then(() => {
          this.drugs = this.drugs.filter(d => d.id !== drugId);
        })
        .catch(error => {
          console.error("Error deleting drug:", error);
        });
    },
  },
  created() {
    this.drugId = this.$route.params.id;
    this.classId = this.$route.params.class_id;
    console.log("Drug ID:", this.drugId);
    console.log("Class ID:", this.classId);

    resourceService.getDrugsByClassId(this.classId)
      .then(response => {
        this.drugs = response.data;
      })
      .catch(error => {
        console.error("Error fetching drugs:", error);
      });
  },
};

</script>

<style>

</style>