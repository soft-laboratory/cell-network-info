interface CellIdentityType {
  operatorAlphaLong?: string; // Available from ANDROID API >= 28
  operatorAlphaShort?: string; // Available from ANDROID API >= 28
}

export interface CellIdentityGsm extends CellIdentityType {
  additionalPlmns?: string[]; // Available from ANDROID API >= 30
  arfcn?: number; // Available from ANDROID API >= 24
  bsic?: number; // Available from ANDROID API >= 24
  cid: number;
  lac: number;
  mcc?: string; // Available from ANDROID API >= 28
  mnc?: string; // Available from ANDROID API >= 28
  mobileNetworkOperator?: string; // Available from ANDROID API >= 28
}

export interface CellIdentityWcdma extends CellIdentityType {
  additionalPlmns?: string[]; // Available from ANDROID API >= 30
  closedSubscriberGroupInfo?: ClosedSubscriberGroupInfo; // Available from ANDROID API >= 30
  cid: number;
  lac: number;
  mcc?: string; // Available from ANDROID API >= 28
  mnc?: string; // Available from ANDROID API >= 28
  mobileNetworkOperator?: string; // Available from ANDROID API >= 28
  psc: number;
  uarfcn?: number; // Available from ANDROID API >= 24
}

/**
 * Available from ANDROID API >= 29
 */
export interface CellIdentityTdscdma extends CellIdentityType {
  additionalPlmns?: string[]; // Available from ANDROID API >= 30
  closedSubscriberGroupInfo?: ClosedSubscriberGroupInfo; // Available from ANDROID API >= 30
  cid: number;
  cpid: number;
  lac: number;
  mcc?: string; // Available from ANDROID API >= 28
  mnc?: string; // Available from ANDROID API >= 28
  mobileNetworkOperator?: string; // Available from ANDROID API >= 28
  uarfcn: number;
}

export interface CellIdentityLte extends CellIdentityType {
  additionalPlmns?: string[]; // Available from ANDROID API >= 30
  closedSubscriberGroupInfo?: ClosedSubscriberGroupInfo; // Available from ANDROID API >= 30
  bands?: number[]; // Available from ANDROID API >= 30
  earfcn?: number; // Available from ANDROID API >= 24
  bandwidth: number; // Available from ANDROID API >= 28
  mcc?: string; // Available from ANDROID API >= 28
  mnc?: string; // Available from ANDROID API >= 28
  mobileNetworkOperator?: string; // Available from ANDROID API >= 28
  pci: number;
  tac: number;
  ci: number;
}

/**
 * Available from ANDROID API >= 29
 */
export interface CellIdentityNr extends CellIdentityType {
  additionalPlmns?: string[]; // Available from ANDROID API >= 30
  bands?: number[]; // Available from ANDROID API >= 30
  nrarfcn: number;
  nci: number;
  mcc?: string;
  mnc?: string;
  mobileNetworkOperator?: string;
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

export interface ClosedSubscriberGroupInfo {
  homeNodebName: string;
  csgIdentity: number;
  csgIndicator: boolean;
}

export type CellIdentity =
  | CellIdentityGsm
  | CellIdentityCdma
  | CellIdentityNr
  | CellIdentityLte
  | CellIdentityTdscdma
  | CellIdentityWcdma;
