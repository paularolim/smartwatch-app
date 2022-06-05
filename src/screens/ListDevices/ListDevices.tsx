import React, {useCallback, useState} from 'react';
import {FlatList, NativeModules} from 'react-native';
import {Button} from '../../components/Button';
import {ScannedDevices} from '../../components/ScannedDevice';
import {Container, ListFooter, ListSeparator, Title} from './styles';

export const ListDevices = () => {
  const [devices, setDevices] =
    useState<Record<'deviceName' | 'deviceAddress', string>[]>();

  const handleDiscoveryDevices = useCallback(() => {
    NativeModules.BluetoothConnection.listDiscoveryDevices(
      (response: Record<string, string>) => {
        const discoveryDevices = [];
        const keys = Object.keys(response);
        const values = Object.values(response);
        keys.forEach((key, index) =>
          discoveryDevices.push({
            deviceName: key,
            deviceAddress: values[index],
          }),
        );
        setDevices(discoveryDevices);
      },
    );
  }, []);

  const renderItem = useCallback(
    ({item}) => (
      <ScannedDevices
        deviceName={item.deviceName}
        deviceAddress={item.deviceAddress}
      />
    ),
    [],
  );

  const renderSeparator = useCallback(() => <ListSeparator />, []);

  const renderFooter = useCallback(
    () => (
      <ListFooter>
        <Button onPress={handleDiscoveryDevices}>Buscar dispositivos</Button>
      </ListFooter>
    ),
    [handleDiscoveryDevices],
  );

  return (
    <Container>
      <Title>Dispositivos</Title>

      <FlatList
        data={devices}
        renderItem={renderItem}
        ItemSeparatorComponent={renderSeparator}
        ListFooterComponent={renderFooter}
      />

      {/* <Button onPress={() => {}}>Request</Button>

      <Button
        onPress={() => {
          NativeModules.BluetoothConnection.isBluetoothEnabled();
        }}>
        isBluetoothEnabled
      </Button>

      <Button
        onPress={() => {
          NativeModules.BluetoothConnection.listPairedDevices();
        }}>
        listPairedDevices
      </Button>

      <Button
        onPress={() => {
          NativeModules.BluetoothConnection.hasBluetoothSupport();
        }}>
        hasBluetoothSupport
      </Button>*/}
    </Container>
  );
};
