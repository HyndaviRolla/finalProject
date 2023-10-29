import { NetworkElement } from "./network-element";

 

export interface RelatedNetworkElement {
  id: number;
  parentNetworkElement: NetworkElement;
  relatedNetworkElement: NetworkElement;
}