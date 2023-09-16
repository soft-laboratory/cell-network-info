interface SignalStrengthType {
  dbm: number;
  asuLevel: number;
  level?: number; // Available from ANDROID API >= 23
}

export interface SignalStrengthCdma extends SignalStrengthType {
  cdmaDbm: number;
  cdmaEcio: number;
  cdmaLevel: number;
  evdoDbm: number;
  evdoEcio: number;
  evdoLevel: number;
  evdoSnr: number;
}

export interface SignalStrengthGsm extends SignalStrengthType {
  bitErrorRate?: number; // Available from ANDROID API >= 29
  rssi?: number; // Available from ANDROID API >= 30
  timingAdvance?: number; // Available from ANDROID API >= 26
}

export interface SignalStrengthLte extends SignalStrengthType {
  cqi?: number; // Available from ANDROID API >= 26
  cqiTableIndex?: number; // Available from ANDROID API >= 31
  rsrp?: number; // Available from ANDROID API >= 26
  rsrq?: number; // Available from ANDROID API >= 26
  rssi?: number; // Available from ANDROID API >= 29
  rssnr?: number; // Available from ANDROID API >= 26
  timingAdvance: number;
}

/**
 * Available from ANDROID API >= 29
 */
export interface SignalStrengthNr extends SignalStrengthType {
  csiCqiReport?: number[]; // Available from ANDROID API >= 31
  csiCqiTableIndex?: number; // Available from ANDROID API >= 31
  csiRsrp: number;
  csiRsrq: number;
  csiSinr: number;
  ssRsrp: number;
  ssRsrq: number;
  ssSinr: number;
}

export interface SignalStrengthTdscdma extends SignalStrengthType {
  rscp: number;
}

export interface SignalStrengthWcdma extends SignalStrengthType {
  ecNo?: number; // Available from ANDROID API >= 30
}

export type SignalStrength =
  | SignalStrengthWcdma
  | SignalStrengthTdscdma
  | SignalStrengthNr
  | SignalStrengthLte
  | SignalStrengthGsm
  | SignalStrengthCdma;
