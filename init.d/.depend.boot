TARGETS = console-setup mountkernfs.sh resolvconf ufw hostname.sh apparmor screen-cleanup plymouth-log udev keyboard-setup cryptdisks cryptdisks-early hwclock.sh networking mountdevsubfs.sh checkroot.sh urandom lvm2 iscsid checkfs.sh open-iscsi kmod mountall.sh checkroot-bootclean.sh bootmisc.sh mountnfs.sh procps mountnfs-bootclean.sh mountall-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
hwclock.sh: mountdevsubfs.sh
networking: mountkernfs.sh urandom resolvconf procps
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
urandom: hwclock.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
iscsid: networking
checkfs.sh: cryptdisks checkroot.sh lvm2
open-iscsi: networking iscsid
kmod: checkroot.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountnfs-bootclean.sh udev mountall-bootclean.sh
mountnfs.sh: networking
procps: mountkernfs.sh udev
mountnfs-bootclean.sh: mountnfs.sh
mountall-bootclean.sh: mountall.sh
