import { NativeModules } from 'react-native';
import NetworkInfoModule from './NetworkInfoModule';

export default new NetworkInfoModule(NativeModules.RNNetworkInfo);
