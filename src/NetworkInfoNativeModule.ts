export default interface NetworkInfoNativeModule {
  /**
   * Informs the RNNetworkInfo native module about the addition of the
   * requested eventType listener.  This enables the specified eventType messages
   * to be sent from native to the React Native.
   *
   * Event listeners on the native side are solely just counters used to determine
   * whether there is a point in  sending the event across the bridge.
   *
   * @param eventType
   */
  addListener(eventType: string): void;

  /**
   * Removes a single listener count of the specified event type.  Once all listeners
   * are removed, the native side will not send a more events.
   *
   * @param eventType
   */
  removeListener(eventType: string): void;

  /**
   * Clears out all listener counts on the native side for the provided event type.   This
   * may need to be separated into a removeAllListeners with no argument that would
   * remove every listener all together.
   *
   * @param eventType
   */
  removeAllListeners(eventType: string): void;
}
