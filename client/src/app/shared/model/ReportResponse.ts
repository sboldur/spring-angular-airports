import {Country} from "./Country";
import {CountryWithAirportsCount} from "./CountryWithAirportsCount";
import {RunwayWithIdentificationsCount} from "./RunwayWithIdentificationsCount";

export class ReportResponse{
  countriesWithHighestNoOfAirports : CountryWithAirportsCount[];
  countriesWithLowestNoOfAirports : CountryWithAirportsCount[];
  mostCommonRunwayIdentifications: RunwayWithIdentificationsCount[];
  runwaySurfacesPerCountry : [Country, Array<String>];

}
