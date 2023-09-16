interface CellIdentityType {
  operatorAlphaLong?: string;
  operatorAlphaShort?: string;
}

export interface CellIdentityGsm extends CellIdentityType {
  additionalPlmns: string[];
  arfcn: number;
  bsic: number;
  cid: number;
  lac: number;
  mccString: string;
  mncString: string;
  mobileNetworkOperator: string;
}

export interface CellIdentityWcdma extends CellIdentityType {
  additionalPlmns: string[];
  cid: number;
  lac: number;
  mccString: string;
  mncString: string;
  mobileNetworkOperator: string;
  psc: number;
  uarfcn: number;
}

export interface CellIdentityTdscdma extends CellIdentityType {
  additionalPlmns: string[];
  cid: number;
  cpid: number;
  lac: number;
  mccString: string;
  mncString: string;
  mobileNetworkOperator: string;
  uarfcn: number;
}

export interface CellIdentityLte extends CellIdentityType {
  additionalPlmns: string[];
  bands: number[];
  earfcn: number;
  bandwidth: number;
  mccString: string;
  mncString: string;
  mobileNetworkOperator: string;
  pci: number;
  tac: number;
  ci: number;
}

export interface CellIdentityNr extends CellIdentityType {
  additionalPlmns: string[];
  bands: number[];
  nrarfcn: number;
  nci: number;
  mccString: string;
  mncString: string;
  mobileNetworkOperator: string;
  pci: number;
  tac: number;
}

export interface CellIdentityCdma extends CellIdentityType {
  basestationId: number;
  latitude: number;
  longitude: number;
  networkId: number;
  systemId: number;
}

export type CellIdentity =
  | CellIdentityGsm
  | CellIdentityCdma
  | CellIdentityNr
  | CellIdentityLte
  | CellIdentityTdscdma
  | CellIdentityWcdma;
