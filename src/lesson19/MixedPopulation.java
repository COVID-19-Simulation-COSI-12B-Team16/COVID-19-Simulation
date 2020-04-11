package lesson19;

public class MixedPopulation extends Population{
  int numShelterInPlace;
  int numEssential;
  int numOthers;
  int numFrequentFliers;
  int numSusceptible;

  public MixedPopulation(int numShelterInPlace, int numEssential, int numOthers, int numFrequentFliers, int numSusceptible){
    super(numShelterInPlace + numEssential + numOthers + numFrequentFliers + numSusceptible);
    this.numShelterInPlace = numShelterInPlace;
    this.numEssential = numEssential;
    this.numOthers = numOthers;
    this.numFrequentFliers = numFrequentFliers;
    this.numSusceptible = numSusceptible;
  }

  public void createPeople(){
    for(int i=0; i<this.numShelterInPlace; i++){
      this.addPerson(new StayAtHome());
    }
    for(int i=0; i<this.numEssential; i++){
      this.addPerson(new StayAtHomeIfSick());
    }
    for(int i=0; i<this.numOthers; i++){
      this.addPerson(new Skeptic());
    }
    for(int i=0; i<this.numFrequentFliers; i++){
      this.addPerson(new FrequentFlier());
    }
    for(int i=0; i<this.numSusceptible; i++) {
      this.addPerson(new Susceptible());
    }
  }
}
