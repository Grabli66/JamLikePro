unit SoundLibraryUnit;

{$mode objfpc}{$H+}

interface

uses {$IFDEF UNIX}
  cthreads,
  cwstring, {$ENDIF}
  SysUtils,
  uos_flat;

// Инициализирует библиотеку
function Init: Integer; stdcall;
//function AddSound(name: PChar): Integer; stdcall;
procedure PlaySound(name: PChar); stdcall;

implementation

var gPlayerIdx: LongInt = 0;

function Init: LongInt; stdcall;
var portAudioPath, sndFilePath, mgpPath: String;
    res: LongInt;
begin
  portAudioPath := 'libs/uos/lib/Linux/64bit/LibPortaudio-64.so';
  sndFilePath := 'libs/uos/lib/Linux/64bit/LibSndFile-64.so';
  mgpPath := 'libs/uos/lib/Linux/64bit/LibMpg123-64.so';

  Result:= uos_LoadLib(Pchar(portAudioPath), Pchar(sndFilePath), PChar(mgpPath), nil, nil);
end;

function AddSound(name: PChar): Integer; stdcall;
var player: Integer;
begin
  player:= gPlayerIdx;
  Inc(gPlayerIdx, 1);
  uos_CreatePlayer(player);
  uos_AddFromFile(player, name);
  uos_AddIntoDevOut(player);
  Result:= player;
end;

procedure PlaySound(name: PChar); stdcall;
  var player: Integer;
begin
  player:= 0;
  uos_CreatePlayer(player);
  uos_AddFromFile(player, name);
  uos_AddIntoDevOut(player);
  uos_Play(player);
end;

end.

