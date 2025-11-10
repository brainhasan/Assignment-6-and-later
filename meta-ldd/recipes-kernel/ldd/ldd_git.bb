SUMMARY = "LDD kernel modules (scull, misc-modules)"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=abc123"

SRC_URI = "git://github.com/brainhasan/Assignment-7-and-later.git;branch=main"

S = "${WORKDIR}/git"

inherit module

KERNEL_MODULE_AUTOLOAD = "misc-modules scull"

do_compile() {
    oe_runmake -C ${S}/misc-modules M=${KERNEL_SRC}
    oe_runmake -C ${S}/scull M=${KERNEL_SRC}
}

do_install() {
    install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
    oe_runmake -C ${S}/misc-modules M=${KERNEL_SRC} modules_install INSTALL_MOD_PATH=${D}
    oe_runmake -C ${S}/scull M=${KERNEL_SRC} modules_install INSTALL_MOD_PATH=${D}
}

