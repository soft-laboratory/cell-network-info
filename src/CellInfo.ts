import type { SignalStrength } from './SignalStrength';
import type { CellIdentity } from './CellIdentity';

export interface CellInfo {
  cellConnectionStatus?: number;
  isRegistered: boolean;
  timestampMillis: string;
  signalStrength?: SignalStrength;
  cellIdentity?: CellIdentity;
}
