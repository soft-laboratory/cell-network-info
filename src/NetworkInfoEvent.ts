import type { SignalStrength } from './types/SignalStrength';
import type { CellInfo } from './types/CellInfo';
import type { DisplayInfo } from './types/DisplayInfo';

/**
 * Available Network Info events. This replaces the events provided by the
 * NativeModule.
 */
export enum NetworkInfoEventType {
  CELL_INFO_CHANGED = 'CELL_INFO_CHANGED',
  SIGNAL_STRENGTH_CHANGED = 'SIGNAL_STRENGTH_CHANGED',
  CARRIER_NETWORK_CHANGED = 'CARRIER_NETWORK_CHANGED',
  DISPLAY_INFO_CHANGED = 'DISPLAY_INFO_CHANGED',
  DATA_CONNECTION_STATE_CHANGED = 'DATA_CONNECTION_STATE_CHANGED',
  DATA_ACTIVITY = 'DATA_ACTIVITY',
  DATA_ACTIVATION_STATE_CHANGED = 'DATA_ACTIVATION_STATE_CHANGED',
  CELL_LOCATION_CHANGED = 'CELL_LOCATION_CHANGED',
  CALL_STATE_CHANGED = 'CALL_STATE_CHANGED',
  CALL_FORWARDING_INDICATOR = 'CALL_FORWARDING_INDICATOR',
  CALL_DISCONNECT_CAUSE = 'CALL_DISCONNECT_CAUSE',
  BARRING_INFO = 'BARRING_INFO',
  ACTIVE_DATA_SUBSCRIPTION_ID_CHANGED = 'ACTIVE_DATA_SUBSCRIPTION_ID_CHANGED',
}

/**
 * NetworkInfoEvent wraps the message content coming from the native module.  In most cases it will
 * contain a data {string} element, although it's now possible (easier) for applications
 * to expand upon the content.  It will be up to the developer to ensure that native and javascript
 * are using the same content type.
 */
export interface NetworkInfoEvent {
  eventType: NetworkInfoEventType;
}

/**
 * State change event used for enable/disable
 */
export interface StateChangeEvent extends NetworkInfoEvent {
  state: string;
  enabled: boolean;
}

/**
 * Signal strength changed events.
 */
export interface SignalStrengthChangedEvent extends NetworkInfoEvent {
  signalStrength: SignalStrength;
}

/**
 * Signal strength changed events.
 */
export interface DisplayInfoChangedEvent extends NetworkInfoEvent {
  displayInfo: DisplayInfo;
}

/**
 * Cell info changed events.
 */
export interface CellInfoChangedEvent extends NetworkInfoEvent {
  cellInfo: CellInfo;
}

/**
 * Cell info changed events.
 */
export interface CarrierNetworkChangedEvent extends NetworkInfoEvent {
  active: boolean;
}

/**
 * RNNetworkInfoModule use event listeners for communication with javascript.
 */
export type NetworkInfoEventListener<T extends NetworkInfoEvent> = (
  event: T
) => void;

/**
 * Used to wrap EmitterSubscription or EventSubscription
 */
export interface NetworkInfoEventSubscription {
  remove(): void;
}
