import {Runway} from "./Runway";

export class Airport {

  id: number;
  ident: String;
  type: String;
  name: String;
  continent;
  isoCountry: String;
  isoRegion: String;
  municipality: String;
  runways: Runway[];
}
