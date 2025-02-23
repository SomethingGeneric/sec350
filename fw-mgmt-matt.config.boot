interfaces {
    ethernet eth0 {
        address 172.16.150.3/24
        description LAN
        hw-id 00:50:56:a1:15:5d
    }
    ethernet eth1 {
        address 172.16.200.2/28
        description MGMT
        hw-id 00:50:56:a1:1e:0d
    }
    loopback lo {
    }
}
nat {
    source {
        rule 10 {
            description "NAT from MGMT to LAN"
            outbound-interface eth0
            source {
                address 172.16.200.0/28
            }
            translation {
                address masquerade
            }
        }
    }
}
protocols {
    rip {
        interface eth0 {
        }
        network 172.16.200.0/28
    }
    static {
        route 0.0.0.0/0 {
            next-hop 172.16.150.2 {
            }
        }
    }
}
service {
    dns {
        forwarding {
            allow-from 172.16.200.0/28
            listen-address 172.16.200.2
            system
        }
    }
    ssh {
        listen-address 0.0.0.0
    }
}
system {
    config-management {
        commit-revisions 100
    }
    conntrack {
        modules {
            ftp
            h323
            nfs
            pptp
            sip
            sqlnet
            tftp
        }
    }
    console {
        device ttyS0 {
            speed 115200
        }
    }
    host-name fw-mgmt-matt
    login {
        user vyos {
            authentication {
                encrypted-password $6$5yPeVikGWyUshlHS$FqIsJxYVC3d7toPl1uFsAGds1GjkYlRNM3er.bjEMcHKFAptZ/6mBk8ne3JZY9DsS1ksutsjV/5kxVj2cwAgx.
            }
        }
    }
    name-server 172.16.150.2
    ntp {
        server time1.vyos.net {
        }
        server time2.vyos.net {
        }
        server time3.vyos.net {
        }
    }
    syslog {
        global {
            facility all {
                level info
            }
            facility protocols {
                level debug
            }
        }
    }
}


// Warning: Do not remove the following line.
// vyos-config-version: "bgp@3:broadcast-relay@1:cluster@1:config-management@1:conntrack@3:conntrack-sync@2:dhcp-relay@2:dhcp-server@6:dhcpv6-server@1:dns-forwarding@3:firewall@7:flow-accounting@1:https@3:interfaces@26:ipoe-server@1:ipsec@9:isis@1:l2tp@4:lldp@1:mdns@1:monitoring@1:nat@5:nat66@1:ntp@1:openconnect@2:ospf@1:policy@3:pppoe-server@5:pptp@2:qos@1:quagga@10:rpki@1:salt@1:snmp@2:ssh@2:sstp@4:system@25:vrf@3:vrrp@3:vyos-accel-ppp@2:wanloadbalance@3:webproxy@2"
// Release version: 1.4-rolling-202209130217
