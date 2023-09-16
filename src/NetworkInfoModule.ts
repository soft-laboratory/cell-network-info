import { NativeEventEmitter } from 'react-native';
import type {
  CellInfoChangedEvent,
  NetworkInfoEvent,
  NetworkInfoEventListener,
  NetworkInfoEventSubscription,
  SignalStrengthChangedEvent,
} from './NetworkInfoEvent';
import { NetworkInfoEventType } from './NetworkInfoEvent';
import type NetworkInfoNativeModule from './NetworkInfoNativeModule';

export default class NetworkInfoModule {
  /**
   * Native RNBluetoothClassicModule provided from Java and IOS through
   * the NativeModules.
   *
   * @private
   */
  _nativeModule: NetworkInfoNativeModule;

  /**
   * NativeEventEmitter - the BluetoothModule used to extend this, but it became
   * apparent that I needed more customizable messaging between JS and Native that
   * the RCTEventEmitter couldn't manage.
   *
   * @private
   */
  _eventEmitter: NativeEventEmitter;

  constructor(nativeModule: NetworkInfoNativeModule) {
    this._nativeModule = nativeModule;
    this._eventEmitter = new NativeEventEmitter(new NativeModule());
  }

  private createNetworkInfoEventSubscription<T extends NetworkInfoEvent>(
    eventType: NetworkInfoEventType,
    listener: NetworkInfoEventListener<T>
  ): NetworkInfoEventSubscription {
    this._nativeModule.addListener(eventType);

    let subscription = this._eventEmitter.addListener(eventType, listener);

    return {
      remove: () => {
        this._nativeModule.removeListener(eventType);
        subscription.remove();
      },
    };
  }

  onCellInfoChanged(
    listener: NetworkInfoEventListener<CellInfoChangedEvent>
  ): NetworkInfoEventSubscription {
    return this.createNetworkInfoEventSubscription(
      NetworkInfoEventType.CELL_INFO_CHANGED,
      listener
    );
  }

  onSignalStrengthChanged(
    listener: NetworkInfoEventListener<SignalStrengthChangedEvent>
  ): NetworkInfoEventSubscription {
    return this.createNetworkInfoEventSubscription(
      NetworkInfoEventType.SIGNAL_STRENGTH_CHANGED,
      listener
    );
  }
}

/**
 * Internal `NativeModule` to get around the fact that React doesn't actually make this
 * type available, but we need it in order to create our NetworkInfoModule.
 */
class NativeModule {
  // @ts-ignore
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  addListener(eventType: string) {}

  // @ts-ignore
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  removeListeners(count: number) {}
}
