export class Country {
  id: number;
  code: String;
  name: String;
  continent: String;


  constructor(id: number, code: String, name: String, continent: String) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.continent = continent;
  }
}
