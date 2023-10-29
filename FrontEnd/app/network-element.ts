  
import { Components } from "./component";
import { RelatedNetworkElement } from "./relatedNetworkElement";
 

export interface NetworkElement {
  id: number;
  name: string; 
  type: string;
  ipAddress: string;
  model:string;  
  manufacturer: string;
  components: Components[];
  relatedNetworkElements?: RelatedNetworkElement[];
}